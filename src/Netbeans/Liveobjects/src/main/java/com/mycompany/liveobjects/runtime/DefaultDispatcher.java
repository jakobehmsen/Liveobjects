package com.mycompany.liveobjects.runtime;

public class DefaultDispatcher implements Dispatcher {
    private ObjectLoader objectLoader;
    private DispatchGroup group;
    
    public DefaultDispatcher(ObjectLoader objectLoader) {
        this.objectLoader = objectLoader;
        
        DispatchGroup customGroup = new DispatchGroup() {
            @Override
            public Instructions.SendI replace(LObject receiver, LObject[] arguments, Environment environment, int selector) {
                return new Instructions.SendI(selector, arguments.length) {
                    @Override
                    public void send(Environment environment, LObject receiver, int symbolCode, LObject[] arguments) {
                        resolveAndSend(receiver, arguments, environment, selector);
                    }
                };
            }
        };
        
        group = new JavaDispatchGroup()
            .withFallback(new PrimitiveDispatchGroup(objectLoader))
            .withFallback(customGroup);
    }

    @Override
    public void send(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        Instructions.SendI sendInstruction2 = group.replace(receiver, arguments, environment, selector);
        environment.currentFrame().replaceInstruction(sendInstruction2);
        sendInstruction2.send(environment, receiver, selector, arguments);
    }
    
    private void resolveAndSend(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        // Could simply resolve the behavior and invoke it
        receiver.send(selector, arguments, environment);
    }

    @Override
    public void sendResumeWithInRet(LObject receiver /*Is always sender of current frame*/, LObject result, Environment environment) {
        if(receiver instanceof Frame) {
            ((Frame)receiver).resumeWith(environment, result);
        } else {
            // TODO: Consider:
            // - Should this be an external frame instead?
            // - Is the sender necessary?
            environment.currentFrame(objectLoader.newFrame(environment.currentFrame().sender(), Instructions.ROOT_INSTRUCTIONS));
            resolveAndSend(receiver, new LObject[]{result}, environment, environment.getSymbolCode("resumeWith:"));
        }
    }
    
    @Override
    public void handlePrimitiveError(Environment environment, Throwable error) {
        LObject sender = environment.currentFrame().sender();
        if(sender instanceof Frame) {
            ((Frame)sender).handlePrimitiveError(environment, error);
        } else {
            // TODO: Consider:
            // - Should this be an external frame instead?
            // - Is the sender necessary?
            environment.currentFrame(objectLoader.newFrame(environment.currentFrame().sender(), Instructions.ROOT_INSTRUCTIONS));
            LObject errorLObject = new JavaInstanceLObject(error);
            resolveAndSend(sender, new LObject[]{errorLObject, (LObject)environment.currentFrame()}, environment, environment.getSymbolCode("handlePrimitiveError:at:"));
        }
    }
}
