/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.java;

/**
 *
 * @author user
 */
public class Constants {
    
    private static String URL;
    private static String USER;
    private static String PASSWD;
    private static boolean FORCE_IND;
    private static boolean CHECK_IND;
    private static boolean FILE2DB_IND;
    
    public static String isURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Constants.URL = URL;
    }

    public static String isUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        Constants.USER = USER;
    }

    public static String isPASSWD() {
        return PASSWD;
    }

    public static void setPASSWD(String PASSWD) {
        Constants.PASSWD = PASSWD;
    }

    public static boolean isFORCE_IND() {
        return FORCE_IND;
    }

    public static void setFORCE_IND(boolean FORCE_IND) {
        Constants.FORCE_IND = FORCE_IND;
    }

    public static boolean isCHECK_IND() {
        return CHECK_IND;
    }

    public static void setCHECK_IND(boolean CHECK_IND) {
        Constants.CHECK_IND = CHECK_IND;
    }

    public static boolean isFILE2DB_IND() {
        return FILE2DB_IND;
    }

    public static void setFILE2DB_IND(boolean FILE2DB_IND) {
        Constants.FILE2DB_IND = FILE2DB_IND;
    }
}
