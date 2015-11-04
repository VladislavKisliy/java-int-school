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

    public CustomerMasterTable(Long customer, String customerName, java.util.Date modDate) {
        this.customer = customer;
        this.customerName = customerName;
        this.modDate = modDate;
    }
    private java.util.Date modDate;

    public void setModDate(java.util.Date modDate) {
        this.modDate = modDate;
    }

    public java.util.Date getModDate() {
        return modDate;
    }

    public CustomerMasterTable() {
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

    @Override
    public String toString() {
        return "CustomerMasterTable{" + "customer=" + customer + ", customerName=" + customerName + ", modDate=" + modDate + '}';
    }
}
