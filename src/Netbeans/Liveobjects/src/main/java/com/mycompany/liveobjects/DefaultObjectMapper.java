package com.mycompany.liveobjects;

import java.lang.reflect.Array;

public class DefaultObjectMapper implements ObjectMapper {
    @Override
    public Object mapToNative(Environment environment, LObject object) {
        if(object == environment.getWorld().getNil()) {
            return null;
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
}
