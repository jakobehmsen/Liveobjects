/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.expr;

import com.mycompany.liveobjects.Instruction;
import java.util.List;

/**
 *
 * @author jakob
 */
public interface Emitter {
    void emit(List<Instruction> instructions);
}
