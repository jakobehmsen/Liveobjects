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
public interface ObjectMapper {

    public Object mapToNative(Environment environment, LObject argumentLObject);

    public LObject mapToLObject(Environment environment, Object responseNative);
    
}
