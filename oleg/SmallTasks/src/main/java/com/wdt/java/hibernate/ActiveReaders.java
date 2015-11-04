/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java.hibernate;

import java.util.Date;

/**
 *
 * @author user
 */
public class ActiveReaders {
    /*
    ID	NUMBER(4)
    BOOK_ID	NUMBER(10)
    USER_ID	NUMBER(10)
    DATE_GOT	DATE
    */
    private int id;
    private int bookId;
    private int userId;
    private Date dateGot;  

    public ActiveReaders(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateGot() {
        return dateGot;
    }

    public void setDateGot(Date dateGot) {
        this.dateGot = dateGot;
    }
}
