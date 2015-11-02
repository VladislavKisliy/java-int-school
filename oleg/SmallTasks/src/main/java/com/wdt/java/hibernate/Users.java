/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wdt.java.hibernate;

import java.util.Date;

/**
 *
 * @author Oleg
 */
public class Users {
    /*
    Name            Null?	Type
    USER_ID         NOT NULL	NUMBER(10)
    USERNAME        NOT NULL	VARCHAR2(100)
    SECOND_NAME     NOT NULL	VARCHAR2(100)
    DATE_OF_BURTH   NOT NULL	DATE
    REGISTERED                  DATE
    SEX                         VARCHAR2(1)

    */
    private int user_id;
    private String username;
    private String second_name;
    private Date date_of_burth;
    private Date registered;
    private char sex;

    public Users(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Date getDate_of_burth() {
        return date_of_burth;
    }

    public void setDate_of_burth(Date date_of_burth) {
        this.date_of_burth = date_of_burth;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
    
    
}
