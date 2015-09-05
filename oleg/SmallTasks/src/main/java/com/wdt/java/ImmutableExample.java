/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Oleg 
 */
public final class ImmutableExample {
    private final String name;
    private final Date localDate;

    public ImmutableExample(String name, Date localDate) {
        this.name = name;
        this.localDate = localDate;
    }

    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return "ImmutableExample{" + "name=" + name + ", localDate=" + localDate + '}';
    }

    public final Date getLocalDate() {
        return (Date) localDate.clone();
    }
    public static void main(String [] argc) throws InterruptedException{
        ImmutableExample tempExample = new ImmutableExample("Oleg",new Date());
        System.out.println(tempExample.toString());
        TimeUnit.SECONDS.sleep(1);
        tempExample.getLocalDate().setDate(0);
        System.out.println(tempExample.toString());
    }
    
}
