/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.hibernate;

import java.util.List;
import javax.smartcardio.TerminalFactory;
import org.hibernate.Session;

/**
 *
 * @author W
 */
public class HibernatePrc {

    public static void main(String[] args) {
        new HibernatePrc();
    }

    public HibernatePrc() {
        Session session = null;
        System.out.println("START");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<CustomerMasterTable> customerMasterTables = session.createCriteria(CustomerMasterTable.class)
                    .setMaxResults(10).list();
            for (CustomerMasterTable customerMasterTable : customerMasterTables) {
                System.out.println("Row: " + customerMasterTable);
            }

            session.flush();
            session.close();
        } catch (Exception e) {
            System.err.println("Failed with error" + e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        System.out.println("END");


    }
}
