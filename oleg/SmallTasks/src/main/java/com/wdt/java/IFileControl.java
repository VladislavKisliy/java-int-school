/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;


/**
 *
 * @author Oleg
 */
public interface IFileControl {
    void    write(String fileName, String textToWrite);
    String  read(String fileName) throws Exception;
    boolean exists(String fileName);
    void    update(String fileName, String newText);
    
    
}
