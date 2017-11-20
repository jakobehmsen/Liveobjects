/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects;

/**
 *
 * @author jakob
 */
public class PrimitiveErrorException extends RuntimeException {
    private LObject error;

    public PrimitiveErrorException(LObject error) {
        this.error = error;
    }

    public LObject getError() {
        return error;
    }
}
