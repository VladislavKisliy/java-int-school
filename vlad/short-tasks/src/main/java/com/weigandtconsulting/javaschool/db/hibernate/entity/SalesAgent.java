package com.weigandtconsulting.javaschool.db.hibernate.entity;
// Generated Oct 28, 2015 4:51:35 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SalesAgent generated by hbm2java
 */
public class SalesAgent  implements java.io.Serializable {


     private int salesAgent;
     private String firstName;
     private String lastName;
     private String description;
     private String saType;
     private BigDecimal plannedSal;
     private Date modDate;
     private String modId;
     private Date creDate;
     private String creId;
     private Set customerMastersForKamId = new HashSet(0);
     private Set customerMastersForSaId = new HashSet(0);
     private Set deliveryPoints = new HashSet(0);

    public SalesAgent() {
    }

	
    public SalesAgent(int salesAgent, String firstName, String lastName, String saType, BigDecimal plannedSal, Date modDate, String modId, Date creDate, String creId) {
        this.salesAgent = salesAgent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.saType = saType;
        this.plannedSal = plannedSal;
        this.modDate = modDate;
        this.modId = modId;
        this.creDate = creDate;
        this.creId = creId;
    }
    public SalesAgent(int salesAgent, String firstName, String lastName, String description, String saType, BigDecimal plannedSal, Date modDate, String modId, Date creDate, String creId, Set customerMastersForKamId, Set customerMastersForSaId, Set deliveryPoints) {
       this.salesAgent = salesAgent;
       this.firstName = firstName;
       this.lastName = lastName;
       this.description = description;
       this.saType = saType;
       this.plannedSal = plannedSal;
       this.modDate = modDate;
       this.modId = modId;
       this.creDate = creDate;
       this.creId = creId;
       this.customerMastersForKamId = customerMastersForKamId;
       this.customerMastersForSaId = customerMastersForSaId;
       this.deliveryPoints = deliveryPoints;
    }
   
    public int getSalesAgent() {
        return this.salesAgent;
    }
    
    public void setSalesAgent(int salesAgent) {
        this.salesAgent = salesAgent;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSaType() {
        return this.saType;
    }
    
    public void setSaType(String saType) {
        this.saType = saType;
    }
    public BigDecimal getPlannedSal() {
        return this.plannedSal;
    }
    
    public void setPlannedSal(BigDecimal plannedSal) {
        this.plannedSal = plannedSal;
    }
    public Date getModDate() {
        return this.modDate;
    }
    
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
    public String getModId() {
        return this.modId;
    }
    
    public void setModId(String modId) {
        this.modId = modId;
    }
    public Date getCreDate() {
        return this.creDate;
    }
    
    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }
    public String getCreId() {
        return this.creId;
    }
    
    public void setCreId(String creId) {
        this.creId = creId;
    }
    public Set getCustomerMastersForKamId() {
        return this.customerMastersForKamId;
    }
    
    public void setCustomerMastersForKamId(Set customerMastersForKamId) {
        this.customerMastersForKamId = customerMastersForKamId;
    }
    public Set getCustomerMastersForSaId() {
        return this.customerMastersForSaId;
    }
    
    public void setCustomerMastersForSaId(Set customerMastersForSaId) {
        this.customerMastersForSaId = customerMastersForSaId;
    }
    public Set getDeliveryPoints() {
        return this.deliveryPoints;
    }
    
    public void setDeliveryPoints(Set deliveryPoints) {
        this.deliveryPoints = deliveryPoints;
    }




}


