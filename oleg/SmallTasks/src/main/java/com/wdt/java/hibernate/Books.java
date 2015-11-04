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
public class Books {
    /*
    Name	Null?               Type
    BOOK_ID             NOT NULL    NUMBER(10)
    AUTHOR              NOT NULL    VARCHAR2(150)
    TITLE               NOT NULL    VARCHAR2(200)
    SHORT_DESCRIPTION               VARCHAR2(2000)
    PUBLISHING_DEPT	NOT NULL    VARCHAR2(100)
    RELEASED            NOT NULL    DATE
    GENERE                          VARCHAR2(25)
    RATING                          NUMBER(3) 
    */
    private int book_id;
    private String author;
    private String title;
    private String short_desc;
    private String publishing_dept;
    private Date released;
    private String genre;
    private int rating;

    public Books(int book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getPublishing_dept() {
        return publishing_dept;
    }

    public void setPublishing_dept(String publishing_dept) {
        this.publishing_dept = publishing_dept;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
}
