package com.mycompany.liveobjects.runtime;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

public class JavaDispatchGroup implements DispatchGroup {
    private static final String NATIVE_PREFIX = "__native__";

    @Override
    public Instructions.SendI replace(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        String selectorStr = environment.getSymbolString(selector);
        
        if(receiver instanceof JavaClassLObject) {
            JavaClassLObject receiverJavaClass = (JavaClassLObject)receiver;
            if(selectorStr.startsWith("new")) {
                Class<?> c = receiverJavaClass.getValue();
                Class<?>[] javaParameterTypes = Arrays.asList(arguments).stream()
                        .map(x -> environment.getObjectMapper().mapToNativeType(environment, x))
                        .toArray(s -> new Class<?>[s]);
                try {
                    Constructor constructor = resolveConstructor(c, javaParameterTypes);

                    return new Instructions.SendI(selector, arguments.length) {
                        @Override
                        public void send(Environment environment, LObject receiver, int symbolCode, LObject[] arguments) {
                            Object[] javaArguments = Arrays.asList(arguments).stream()
                                    .map(x -> environment.getObjectMapper().mapToNative(environment, x))
                                    .toArray(s -> new Object[s]);
                            try {
                                Object javaInstance = constructor.newInstance(javaArguments);
                                LObject value = environment.getObjectMapper().mapToLObject(environment, javaInstance);
                                environment.currentFrame().load(value);
                                environment.currentFrame().incIP();
                            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                throw new RuntimeException("Could not instantiate via " + constructor + " with " + Arrays.toString(javaArguments) + ".", ex);
                            }
                        }
                    };
                } catch (NoSuchMethodException | IllegalArgumentException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                return createMethodInvokeInstruction(receiver, arguments, environment, selector, new MethodInvokeInstructionStrategy() {
                    @Override
                    public Class<?> getClass(LObject receiver, LObject[] arguments, Environment environment, int selector) {
                        JavaClassLObject receiverJavaClass = (JavaClassLObject)receiver;
                        return receiverJavaClass.getValue();
                    }

                    @Override
                    public Object invoke(LObject receiver, LObject[] arguments, Environment environment, int selector, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                        Object[] javaArguments = Arrays.asList(arguments).stream()
                                .map(x -> environment.getObjectMapper().mapToNative(environment, x))
                                .toArray(s -> new Object[s]);
                        return method.invoke(null, javaArguments);
                    }

                    @Override
                    public Instructions.SendI noSuchMethod(LObject receiver, LObject[] arguments, Environment environment, int selector, String methodName, Class<?> c) {
                        if(arguments.length == 0) {
                            // Maybe a field matches
                            String fieldName = methodName;
                            try {
                                Field field = c.getField(fieldName);

                                return new Instructions.SendI(selector, 0) {
                                    @Override
                                    public void send(Environment environment, LObject receiver, int symbolCode, LObject[] arguments) {
                                        try {
                                            Object javaInstance = field.get(null);
                                            LObject value = environment.getObjectMapper().mapToLObject(environment, javaInstance);
                                            environment.currentFrame().load(value);
                                            environment.currentFrame().incIP();
                                        } catch (IllegalAccessException | IllegalArgumentException ex) {
                                            throw new RuntimeException("Could not access field " + field + ".", ex);
                                        }
                                    }
                                };
                            } catch (NoSuchFieldException | SecurityException ex1) {
                                throw new RuntimeException(ex1);
                            }
                        } else {
                            throw new RuntimeException(new NoSuchMethodException(methodName));
                        }
                    }
                });
            }
        } else if(receiver instanceof JavaInstanceLObject) {
            return createMethodInstanceInvokeInstruction(receiver, arguments, environment, selector);
        } else if(selectorStr.startsWith(NATIVE_PREFIX)) {
            String selectorStrWithOutPrefix = selectorStr.substring(NATIVE_PREFIX.length());
            selector = environment.getSymbolCode(selectorStrWithOutPrefix);
            return createMethodInstanceInvokeInstruction(receiver, arguments, environment, selector);
        }
        
        return null;
    }
    
    private Instructions.SendI createMethodInstanceInvokeInstruction(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        return createMethodInvokeInstruction(receiver, arguments, environment, selector, new MethodInvokeInstructionStrategy() {
            @Override
            public Class<?> getClass(LObject receiver, LObject[] arguments, Environment environment, int selector) {
                Object target = receiver.toNative(environment);
                return target.getClass();
            }

            @Override
            public Object invoke(LObject receiver, LObject[] arguments, Environment environment, int selector, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                Object target = receiver.toNative(environment);
                Object[] javaArguments = Arrays.asList(arguments).stream()
                        .map(x -> environment.getObjectMapper().mapToNative(environment, x))
                        .toArray(s -> new Object[s]);
                return method.invoke(target, javaArguments);
            }

            @Override
            public Instructions.SendI noSuchMethod(LObject receiver, LObject[] arguments, Environment environment, int selector, String methodName, Class<?> c) {
                throw new RuntimeException(new NoSuchMethodException(methodName));
            }
            });
    }
    
    private interface MethodInvokeInstructionStrategy {
        Class<?> getClass(LObject receiver, LObject[] arguments, Environment environment, int selector);
        Object invoke(LObject receiver, LObject[] arguments, Environment environment, int selector, Method method)
                throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
        Instructions.SendI noSuchMethod(LObject receiver, LObject[] arguments, Environment environment, int selector, String methodName, Class<?> c);
    }
    
    private Instructions.SendI createMethodInvokeInstruction(LObject receiver, LObject[] arguments, Environment environment, int selector, MethodInvokeInstructionStrategy strategy) {
        Class<?> c = strategy.getClass(receiver, arguments, environment, selector);
        Class<?>[] javaParameterTypes = Arrays.asList(arguments).stream()
                .map(x -> environment.getObjectMapper().mapToNativeType(environment, x))
                .toArray(s -> new Class<?>[s]);
        String selectorStr = environment.getSymbolString(selector);
        int end = selectorStr.contains(":") ? selectorStr.indexOf(":") : selectorStr.length();
        String methodName = selectorStr.substring(0, end);
        
        try {
            Method method = resolveMethod(c, methodName, javaParameterTypes);

            return new Instructions.SendI(selector, arguments.length) {
                @Override
                public void send(Environment environment, LObject receiver, int symbolCode, LObject[] arguments) {
                    try {
                        Object javaInstance = strategy.invoke(receiver, arguments, environment, symbolCode, method);
                        LObject value = environment.getObjectMapper().mapToLObject(environment, javaInstance);
                        environment.currentFrame().load(value);
                        environment.currentFrame().incIP();
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        throw new RuntimeException("Could not invoke method " + method + " with " + Arrays.toString(arguments) + ".", ex);
                    }
                }
            };
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchMethodException ex) {
            return strategy.noSuchMethod(receiver, arguments, environment, selector, methodName, c);
        }
    }
    
    private Constructor<?> resolveConstructor(Class<?> c, Class<?>[] parameterTypes) throws NoSuchMethodException {
        return this.<Constructor<?>>resolveMember(c, parameterTypes, MemberResolver.Constructor);
    }
    
    private Method resolveMethod(Class<?> c, String name, Class<?>[] parameterTypes) throws NoSuchMethodException {
        return this.<Method>resolveMember(c, parameterTypes, MemberResolver.Method.named(name));
    }
    
    private interface MemberResolver<T extends Member> {
        T resolve(Class<?> c, Class<?>[] parameterTypes) throws NoSuchMethodException, SecurityException;
        
        public static MemberResolver Constructor = new MemberResolver<Constructor>() {
            @Override
            public Constructor resolve(Class c, Class[] parameterTypes) throws NoSuchMethodException, SecurityException {
                return c.getConstructor(parameterTypes);
            }
        };
        
        public static class Method {
            public static MemberResolver<java.lang.reflect.Method> named(String name) {
                return new MemberResolver<java.lang.reflect.Method>() {
                    @Override
                    public java.lang.reflect.Method resolve(Class<?> c, Class<?>[] parameterTypes) throws NoSuchMethodException, SecurityException {
                        return c.getMethod(name, parameterTypes);
                    }
                };
            }
        }
    }
    
    private <T extends Member> T resolveMember(Class<?> c, Class<?>[] parameterTypes, MemberResolver<T> memberResolver) throws NoSuchMethodException {
        return resolveMember(c, parameterTypes, memberResolver, new Class<?>[0], 0);
    }
                
    private <T extends Member> T resolveMember(Class<?> c, Class<?>[] parameterTypes, MemberResolver<T> memberResolver, Class<?>[] parameterTypeCandidates, int i) throws NoSuchMethodException {
        if(parameterTypeCandidates.length == parameterTypes.length) {
            return memberResolver.resolve(c, parameterTypeCandidates);
        }

        Class parameterType = parameterTypes[i];

        while(parameterType != null) {
            Class<?>[] parameterTypeCandidates2 = new Class<?>[parameterTypeCandidates.length + 1];
            System.arraycopy(parameterTypeCandidates, 0, parameterTypeCandidates2, 0, parameterTypeCandidates.length);
            parameterTypeCandidates2[parameterTypeCandidates.length] = parameterType;

            try {
                return resolveMember(c, parameterTypes, memberResolver, parameterTypeCandidates2, i + 1);
            } catch(NoSuchMethodException e) {

            }

            parameterType = parameterType.getSuperclass();
            
            // Investigate interfaces also
        }

        throw new NoSuchMethodException();
    }
}
