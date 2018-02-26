package com.mycompany.liveobjects.expr;

import com.mycompany.liveobjects.runtime.Instruction;
import java.util.List;

public interface Emitter {
    void emit(List<Instruction> instructions);
}
