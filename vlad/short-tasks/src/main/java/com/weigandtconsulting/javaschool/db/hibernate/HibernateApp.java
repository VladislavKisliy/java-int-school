/*
 * Copyright (C) 2015 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.db.hibernate;

import com.weigandtconsulting.javaschool.db.hibernate.entity.CustomerContract;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author vlad
 */
public class HibernateApp {

    private final static int MAX_RESULT = 5;

    private static void displayResult(List resultList) {
        System.out.println("---------");
        System.out.println(" -- List =" + resultList + " --");
        for (Object line : resultList) {
            System.out.println("Line =" + line);
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // HQL
            List<CustomerContract> customerContract = (List<CustomerContract>) session.createQuery("from CustomerContract order by contract").setMaxResults(MAX_RESULT).list();
            displayResult(customerContract);

            // SQL
            List<CustomerContract> customerContractSQL = (List<CustomerContract>) session.createSQLQuery("select contract, description, status from CUSTOMER_CONTRACT")
                    .addScalar("contract").addScalar("description").addScalar("status")
                    .setResultTransformer(Transformers.aliasToBean(CustomerContract.class)).setMaxResults(MAX_RESULT).list();
            displayResult(customerContractSQL);

            // Criteria
            List<CustomerContract> customerContractCr = (List<CustomerContract>) session.createCriteria(CustomerContract.class)
                    .add(Restrictions.le("status", "I"))
                    .setMaxResults(MAX_RESULT).list();
            displayResult(customerContractCr);
            
//            Transaction tx = session.beginTransaction();
//            tx.setTimeout(5);
//    		//doSomething(session);
//            tx.commit();

            session.flush();
            session.close();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

}
