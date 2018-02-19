package com.mycompany.liveobjects;

public interface DispatchGroup {
    Instructions.SendI replace(LObject receiver, LObject[] arguments, Environment environment, int selector);
    
    default DispatchGroup withFallback(DispatchGroup fallbackGroup) {
        DispatchGroup thisGroup = this;
        
        return new DispatchGroup() {
            @Override
            public Instructions.SendI replace(LObject receiver, LObject[] arguments, Environment environment, int selector) {
                Instructions.SendI replacement = thisGroup.replace(receiver, arguments, environment, selector);
                if(replacement != null) {
                    return thisGroup.replace(receiver, arguments, environment, selector);
                } else {
                    return fallbackGroup.replace(receiver, arguments, environment, selector);
                }
            }
        };
    }
}
