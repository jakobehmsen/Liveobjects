package com.mycompany.liveobjects.runtime;

import java.lang.reflect.Array;

public class DefaultObjectMapper implements ObjectMapper {
    @Override
    public Object mapToNative(Environment environment, LObject object) {
        if(object == environment.getWorld().getNil()) {
            return null;
        } else if(object == environment.getWorld().getTrue()) {
            return true;
        } else if(object == environment.getWorld().getFalse()) {
            return false;
        }
        
        return object.toNative(environment);
    }

    @Override
    public LObject mapToLObject(Environment environment, Object object) {
        if(object == null) {
            return environment.getWorld().getNil();
        }
        
        switch(object.getClass().getName()) {
            case "java.lang.String":
                return new StringLObject((String)object);
            case "java.lang.Integer":
                return new IntegerLObject((int)object);
            case "java.lang.Boolean":
                return environment.getWorld().getBoolean((boolean)object);
        }
        
        if(object.getClass().isArray()) {
            int length = Array.getLength(object);
            LObject[] items = new LObject[length];
            for(int i = 0; i < length; i++) {
                LObject item = environment.getObjectMapper().mapToLObject(environment, Array.get(object, i));
                items[i] = item;
            }
            return environment.getObjectLoader().newArray(items);
        }
        
        return environment.getObjectLoader().newNative(object);
    }

    @Override
    public Class<?> mapToNativeType(Environment environment, LObject object) {
        if(object == environment.getWorld().getNil()) {
            return Object.class;
        } else if(object == environment.getWorld().getTrue()) {
            return boolean.class;
        } else if(object == environment.getWorld().getFalse()) {
            return boolean.class;
        }
        
        return object.toNativeType(environment);
    }
}
