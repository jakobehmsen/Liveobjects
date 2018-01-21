/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.expr;

/**
 *
 * @author jakob
 */
public interface Expression {
    Emitter compile(boolean asExpression);
}
