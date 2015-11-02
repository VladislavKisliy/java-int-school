/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.hibernate;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author W
 */
public class HibernatePrc {

    public static void main(String[] args) {
        new HibernatePrc();
    }
    Session session = null;

    public HibernatePrc() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<CustomerMasterTable> customerMasterTables = session.createCriteria(CustomerMasterTable.class)
                    .setMaxResults(10).list();
            for (CustomerMasterTable customerMasterTable : customerMasterTables) {
                System.out.println("Row: " + customerMasterTable);
            }
            session.close();
        } catch (Exception e) {
            System.err.println("Failed with error" + e);
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }
}
