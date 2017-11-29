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
        addPrimitive("evaluateAs:", (receiver, arguments, environment) -> {
            LObject receiverArg = arguments[0];
            Block blockReceiver = (Block)receiver;
            blockReceiver.evaluate(receiverArg, new LObject[0], environment);
        });
        addPrimitive("evaluateAs:from:", (receiver, arguments, environment) -> {
            LObject receiverArg = arguments[1];
            LObject senderArg = arguments[0];
            Block blockReceiver = (Block)receiver;
            blockReceiver.evaluate(receiverArg, new LObject[0], environment, (Frame) senderArg);
        });
        addPrimitive("resumeWith:", (receiver, arguments, environment) -> {
            LObject result = arguments[0];
            ((DefaultFrame)receiver).resumeWith(environment, result);
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
        addPrimitive("getIntegerPrototype", (receiver, arguments, environment) -> {
            LObject value = environment.getWorld().getIntegerPrototype();
            environment.currentFrame().load(value);
            environment.currentFrame().incIP();
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
                environment.currentFrame().handlePrimitiveError(environment, new StringLObject("Receiver and/or argument are not integers."));
            }
        });
    }

    @Override
    public void send(LObject receiver, LObject[] arguments, Environment environment, int selector) {
        Behavior primitive = primitives.get(selector);
        if(primitive != null) {
            primitive.invoke(receiver, arguments, environment);
        } else {
            // Could simply resolve the behavior and invoke it
            receiver.send(selector, arguments, environment);
        }
    }

    @Override
    public void registerSymbols(Environment environment) {
        primitives.keySet().forEach(symbolCode -> {
            String string = primitiveSelectors.get(symbolCode);
            environment.addSymbol(symbolCode, string);
        });
    }
}
