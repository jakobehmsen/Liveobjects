package com.mycompany.liveobjects;

import java.util.Hashtable;
import java.util.function.BiFunction;


public class DefaultDispatcher implements Dispatcher {
    private Hashtable<Integer, Behavior> primitives = new Hashtable<>();
    private Hashtable<Integer, String> primitiveSelectors = new Hashtable<>();

    {
        addPrimitive("clone", (receiver, arguments, environment) -> {
            LObject value = receiver.cloneObject(environment);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("evaluate", (receiver, arguments, environment) -> {
            Closure blockReceiver = (Closure)receiver;
            blockReceiver.evaluate(new LObject[0], environment);
        });
        addPrimitive("evaluateAs:", (receiver, arguments, environment) -> {
            LObject receiverArg = arguments[0];
            Behavior blockReceiver = (Behavior)receiver;
            blockReceiver.invoke(receiverArg, new LObject[0], environment);
        });
        addPrimitive("evaluateAs:from:", (receiver, arguments, environment) -> {
            LObject receiverArg = arguments[1];
            LObject senderArg = arguments[0];
            Block blockReceiver = (Block)receiver;
            blockReceiver.evaluate(receiverArg, new LObject[0], environment, senderArg);
        });
        addPrimitive("sender", (receiver, arguments, environment) -> {
            LObject value = (LObject) ((DefaultFrame)receiver).sender();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("getSlot:", (receiver, arguments, environment) -> {
            LObject value = receiver.getSlot(environment, arguments);
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
        });
        addPrimitive("setSlot:to:", (receiver, arguments, environment) -> {
            LObject value = receiver.setSlot(environment, arguments);
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
        
        addPrimitive("resumeWith:", (receiver, arguments, environment) -> {
            //sendResumeWith(receiver, arguments[0], environment);
            if(receiver instanceof Frame) {
                ((Frame)receiver).resumeWith(environment, arguments[0]);
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("resumeWith:"));
            }
        });
        addPrimitive("handlePrimitiveError:at:", (receiver, arguments, environment) -> {
            if(receiver instanceof Frame) {
                ((Frame)receiver).handlePrimitiveError(environment, arguments[0]);
            } else {
                resolveAndSend(receiver, arguments, environment, environment.getSymbolCode("handlePrimitiveError:at:"));
            }
        });
        addIntegerBiFunction("addi:", (lhs, rhs) -> lhs + rhs);
        addIntegerBiFunction("subi:", (lhs, rhs) -> lhs - rhs);
        addIntegerBiFunction("muli:", (lhs, rhs) -> lhs * rhs);
        addIntegerBiFunction("divi:", (lhs, rhs) -> lhs / rhs);
    }

    private void addPrimitive(String selector, Behavior primitive) {
        int symbolCode = primitives.size();
        primitives.put(symbolCode, primitive);
        primitiveSelectors.put(symbolCode, selector);
    }

    private void addIntegerBiFunction(String selector, BiFunction<Integer, Integer, Integer> function) {
        addPrimitive(selector, (receiver, arguments, environment) -> {
            if(receiver instanceof IntegerLObject && arguments[0] instanceof IntegerLObject) {
                IntegerLObject lhs = (IntegerLObject) receiver;
                IntegerLObject rhs = (IntegerLObject) arguments[0];
                int value = function.apply(lhs.getValue(), rhs.getValue());
                environment.currentFrame().loadInteger(value);
                environment.currentFrame().incIP();
            } else {
                handlePrimitiveError(environment, new StringLObject("Receiver and/or argument are not integers."));
            }
        });
    }

    @Override
    public void send(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        Behavior primitive = primitives.get(selector);
        if(primitive != null) {
            primitive.invoke(receiver, arguments, environment);
        } else {
            /*// Could simply resolve the behavior and invoke it
            receiver.send(selector, arguments, environment);*/
            
            resolveAndSend(receiver, arguments, environment, selector);
        }
    }
    
    private void resolveAndSend(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        // Could simply resolve the behavior and invoke it
        receiver.send(selector, arguments, environment);
    }

    @Override
    public void registerSymbols(Environment environment) {
        primitives.keySet().forEach(symbolCode -> {
            String string = primitiveSelectors.get(symbolCode);
            environment.addSymbol(symbolCode, string);
        });
    }

    @Override
    public void sendResumeWithInRet(LObject receiver /*Is always sender of current frame*/, LObject result, Environment environment) {
        if(receiver instanceof Frame) {
            ((Frame)receiver).resumeWith(environment, result);
        } else {
            environment.currentFrame(new DefaultFrame(environment.currentFrame().sender(), new Instruction[] {
                Instructions.loadInteger(0), // Dummy instruction; is always ignored due to ip incr
                Instructions.finish()
            }));
            resolveAndSend(receiver, new LObject[]{result}, environment, environment.getSymbolCode("resumeWith:"));
        }
    }
    
    @Override
    public void handlePrimitiveError(Environment environment, LObject error) {
        LObject sender = environment.currentFrame().sender();
        if(sender instanceof Frame) {
            ((Frame)sender).handlePrimitiveError(environment, error);
        } else {
            environment.currentFrame(new DefaultFrame(environment.currentFrame().sender(), new Instruction[] {
                Instructions.loadInteger(0), // Dummy instruction; is always ignored due to ip incr
                Instructions.finish()
            }));
            resolveAndSend(sender, new LObject[]{error, (LObject)environment.currentFrame()}, environment, environment.getSymbolCode("handlePrimitiveError:at:"));
        }
    }
}
