/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.lang;

/**
 *
 * @author jakob
 */
public interface ErrorHandler {

    public void syntaxError(int line, int column, String message);
    
}
