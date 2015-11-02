/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdt.hibernate;

import java.sql.Date;

/**
 *
 * @author W
 */
public class CustomerMasterTable {
    private Long customer;
    private String customerName;
    private Date modName;

    public CustomerMasterTable(Long customer, String customerName, Date modName) {
        this.customer = customer;
        this.customerName = customerName;
        this.modName = modName;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getModName() {
        return modName;
    }

    public void setModName(Date modName) {
        this.modName = modName;
    }

    @Override
    public String toString() {
        return "CustomerMasterTable{" + "customer=" + customer + ", customerName=" + customerName + ", modName=" + modName + '}';
    }   
}
