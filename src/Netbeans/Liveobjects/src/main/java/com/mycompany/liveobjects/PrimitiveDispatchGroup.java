package com.mycompany.liveobjects;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PrimitiveDispatchGroup implements DispatchGroup {
    private ObjectLoader objectLoader;
    private Hashtable<String, Behavior> primitives = new Hashtable<>();
    //private Hashtable<Integer, String> primitiveSelectors = new Hashtable<>();
    
    public PrimitiveDispatchGroup(ObjectLoader objectLoader) {
        this.objectLoader = objectLoader;
        
        addPrimitive("clone", (receiver, arguments, environment) -> {
            LObject value = receiver.cloneObject(environment);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("isSame:", (receiver, arguments, environment) -> {
            LObject other = arguments[0];
            LObject value = environment.getWorld().getBoolean(receiver == other);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("new:", (receiver, arguments, environment) -> {
            if(receiver == environment.getWorld().getArrayPrototype()) {
                IntegerLObject length = (IntegerLObject) arguments[0];
                LObject[] items = new LObject[length.getValue()];
                Arrays.fill(items, environment.getWorld().getNil());
                ArrayLObject array = objectLoader.newArray(items);
                environment.currentFrame().load(array);
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("new:"));
            }
        });
        addPrimitive("at:", (receiver, arguments, environment) -> {
            if(receiver instanceof ArrayLObject) {
                ArrayLObject array = (ArrayLObject) receiver;
                IntegerLObject index = (IntegerLObject) arguments[0];
                environment.currentFrame().load(array.get(environment, index.getValue()));
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("at:"));
            }
        });
        addPrimitive("at:set:", (receiver, arguments, environment) -> {
            if(receiver instanceof ArrayLObject) {
                ArrayLObject array = (ArrayLObject) receiver;
                IntegerLObject index = (IntegerLObject) arguments[1];
                LObject obj = arguments[0];
                array.set(environment, index.getValue(), obj);
                environment.currentFrame().load(obj);
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("at:set:"));
            }
        });
        addPrimitive("length", (receiver, arguments, environment) -> {
            if(receiver instanceof ArrayLObject) {
                ArrayLObject array = (ArrayLObject) receiver;
                environment.currentFrame().load(new IntegerLObject(array.length(environment)));
                environment.currentFrame().incIP();
            } else if(receiver instanceof StringLObject) {
                StringLObject string = (StringLObject) receiver;
                environment.currentFrame().load(new IntegerLObject(string.getValue().length()));
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("length"));
            }
        });
        addPrimitive("evaluate", (receiver, arguments, environment) -> {
            if(receiver instanceof Closure) {
                Closure blockReceiver = (Closure)receiver;
                blockReceiver.evaluate(new LObject[0], environment);
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("evaluate"));
            }
        });
        addPrimitive("frame", (receiver, arguments, environment) -> {
            if(receiver instanceof Closure) {
                Closure closureReceiver = (Closure)receiver;
                LObject value = closureReceiver.getFrame();
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("frame"));
            }
        });
        addPrimitive("evaluateAs:", (receiver, arguments, environment) -> {
            if(receiver instanceof Behavior) {
                LObject receiverArg = arguments[0];
                Behavior behaviorReceiver = (Behavior)receiver;
                behaviorReceiver.invoke(receiverArg, new LObject[0], environment);
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("evaluateAs:"));
            }
        });
        addPrimitive("evaluateAs:from:", (receiver, arguments, environment) -> {
            if(receiver instanceof Block) {
                LObject receiverArg = arguments[1];
                LObject senderArg = arguments[0];
                Block blockReceiver = (Block)receiver;
                blockReceiver.evaluate(receiverArg, new LObject[0], environment, senderArg);
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("evaluateAs:from:"));
            }
        });
        addPrimitive("sender", (receiver, arguments, environment) -> {
            if(receiver instanceof Frame) {
                LObject value = ((Frame)receiver).sender();
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("sender"));
            }
        });
        addPrimitive(PrimitiveSelectors.RESOLVE_SLOT, (receiver, arguments, environment) -> {
            StringLObject selector = (StringLObject)arguments[0];
            int symbolCode = environment.getSymbolCode(selector.getValue());
            LObject value = receiver.resolve(symbolCode, environment);
            if(value != null) {
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
            } else {
                AssociativeArrayLObject.sendCannotResolveSlotError(environment, selector.getValue());
            }
        });
        addPrimitive(PrimitiveSelectors.GET_SLOT, (receiver, arguments, environment) -> {
            StringLObject selector = (StringLObject)arguments[0];
            LObject value = receiver.getSlot(environment, selector.getValue());
            if(value != null) {
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
            } else {
                AssociativeArrayLObject.sendCannotResolveSlotError(environment, selector.getValue());
            }
        });
        addPrimitive(PrimitiveSelectors.SET_SLOT, (receiver, arguments, environment) -> {
            LObject value = receiver.setSlot(environment, arguments);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive(PrimitiveSelectors.SET_PARENT_SLOT, (receiver, arguments, environment) -> {
            LObject value = receiver.setParentSlot(environment, arguments);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive(PrimitiveSelectors.HAS_SLOT, (receiver, arguments, environment) -> {
            StringLObject selector = (StringLObject)arguments[0];
            boolean b = receiver.hasSlot(environment, selector.getValue());
            LObject value = environment.getWorld().getBoolean(b);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive(PrimitiveSelectors.GET_SLOT_SELECTORS, (receiver, arguments, environment) -> {
            String[] selectors = receiver.getSlotSelectors(environment);
            LObject[] items = Arrays.asList(selectors).stream()
                    .map(s -> new StringLObject(s))
                    .toArray(s -> new LObject[s]);
            ArrayLObject value = environment.getObjectLoader().newArray(items);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive(PrimitiveSelectors.IS_PARENT_SLOT, (receiver, arguments, environment) -> {
            StringLObject selector = (StringLObject)arguments[0];
            boolean b = receiver.isParentSlot(environment, selector.getValue());
            LObject value = environment.getWorld().getBoolean(b);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        
        addPrimitive("primRoot", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getRoot();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primIntegerProto", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getIntegerPrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primFrameProto", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getFramePrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primClosureProto", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getClosurePrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primTrue", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getTrue();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primFalse", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getFalse();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primArrayProto", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getArrayPrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primBlockProto", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getBlockPrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primStringProto", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getStringPrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("primJava", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getJava();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        
        addPrimitive("resumeWith:", (receiver, arguments, environment) -> {
            //sendResumeWith(receiver, arguments[0], environment);
            if(receiver instanceof Frame) {
                ((Frame)receiver).resumeWith(environment, arguments[0]);
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("resumeWith:"));
            }
        });
        addPrimitive("getDistant:at:", (receiver, arguments, environment) -> {
            if(receiver instanceof Frame) {
                IntegerLObject ordinal = (IntegerLObject) arguments[1];
                IntegerLObject contextDistance = (IntegerLObject) arguments[0];
                LObject obj = ((Frame)receiver).getDistant(environment, contextDistance.getValue(), ordinal.getValue());
                environment.currentFrame().load(obj);
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("getDistant:at:"));
            }
        });
        addPrimitive("setDistant:at:to:", (receiver, arguments, environment) -> {
            if(receiver instanceof Frame) {
                LObject value = (IntegerLObject) arguments[0];
                IntegerLObject contextDistance = (IntegerLObject) arguments[1];
                IntegerLObject ordinal = (IntegerLObject) arguments[2];
                ((Frame)receiver).setDistant(environment, contextDistance.getValue(), ordinal.getValue(), value);
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("setDistant:at:to:"));
            }
        });
        addPrimitive("handlePrimitiveError:at:", (receiver, arguments, environment) -> {
            if(receiver instanceof Frame) {
                JavaInstanceLObject<? extends Throwable> primitiveError = (JavaInstanceLObject<? extends Throwable>) arguments[0];
                ((Frame)receiver).handlePrimitiveError(environment, primitiveError.getValue());
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("handlePrimitiveError:at:"));
            }
        });
        addPrimitive("class:", (receiver, arguments, environment) -> {
            if(receiver == environment.getWorld().getJava()) {
                StringLObject className = (StringLObject) arguments[0];
                    
                try {
                    Class<?> c = Class.forName(className.getValue());
                    LObject value = new JavaClassLObject(c);
                    environment.currentFrame().load(value);
                    environment.currentFrame().incIP();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException("Cannot resolve Java class '" + className.getValue() + "'", ex);
                }
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("class:"));
            }
        });
        addIntegerBiFunction("addi:", (lhs, rhs) -> lhs + rhs);
        addIntegerBiFunction("subi:", (lhs, rhs) -> lhs - rhs);
        addIntegerBiFunction("muli:", (lhs, rhs) -> lhs * rhs);
        addIntegerBiFunction("divi:", (lhs, rhs) -> lhs / rhs);
        addIntegerBooleanBiFunction("eqi:", (lhs, rhs) -> lhs.intValue() == rhs);
        addIntegerBooleanBiFunction("lti:", (lhs, rhs) -> lhs < rhs);
        addIntegerBooleanBiFunction("ltei:", (lhs, rhs) -> lhs <= rhs);
        addIntegerBooleanBiFunction("gti:", (lhs, rhs) -> lhs > rhs);
        addIntegerBooleanBiFunction("gtei:", (lhs, rhs) -> lhs >= rhs);
        
        this.<StringLObject, String, Boolean>addBiFunction(
            "eqs:", 
            (lhs, rhs) -> 
                lhs.equals(rhs), o -> o.getValue(), 
            (e, r) -> 
                e.currentFrame().load(Instructions.LoadBool.wrap(r, e))
        );
    }
    
    private void addPrimitive(String selector, Behavior primitive) {
        //int symbolCode = primitives.size();
        primitives.put(selector, primitive);
        //primitiveSelectors.put(symbolCode, selector);
    }

    private void addIntegerBiFunction(String selector, BiFunction<Integer, Integer, Integer> function) {
        this.<IntegerLObject, Integer, Integer>addBiFunction(
            selector, 
            function, o -> o.getValue(), 
            (e, r) -> e.currentFrame().loadInteger(r)
        );
    }

    private void addIntegerBooleanBiFunction(String selector, BiFunction<Integer, Integer, Boolean> function) {
        this.<IntegerLObject, Integer, Boolean>addBiFunction(
            selector, 
            function, o -> o.getValue(), 
            (e, r) -> 
                e.currentFrame().load(Instructions.LoadBool.wrap(r, e))
        );
    }

    private <O extends LObject, T, R> void addBiFunction(String selector, BiFunction<T, T, R> function, Function<O, T> toValueFunction, BiConsumer<Environment, R> frameLoader) {
        addPrimitive(selector, (receiver, arguments, environment) -> {
            LObject lhs = receiver;
            LObject rhs = arguments[0];
            R value = function.apply(toValueFunction.apply((O) lhs), toValueFunction.apply((O) rhs));
            frameLoader.accept(environment, value);
            environment.currentFrame().incIP();
        });
    }
    
    private void resolveAndSend(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        // Could simply resolve the behavior and invoke it
        receiver.send(selector, arguments, environment);
    }

    @Override
    public boolean handles(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        String selectorStr = environment.getSymbolString(selector);
        return primitives.containsKey(selectorStr);
    }

    @Override
    public Instructions.SendI replace(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        String selectorStr = environment.getSymbolString(selector);
        Behavior primitive = primitives.get(selectorStr);
        return new Instructions.SendI(selector, arguments.length) {
            @Override
            public void send(Environment environment, LObject receiver, int symbolCode, LObject[] arguments) {
                primitive.invoke(receiver, arguments, environment);
            }
        };
    }
}
