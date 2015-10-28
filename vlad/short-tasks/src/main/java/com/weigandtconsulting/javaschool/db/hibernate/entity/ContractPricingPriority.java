package com.weigandtconsulting.javaschool.db.hibernate.entity;
// Generated Oct 28, 2015 4:51:35 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * ContractPricingPriority generated by hbm2java
 */
public class ContractPricingPriority  implements java.io.Serializable {


     private BigDecimal contractId;
     private CustomerContract customerContract;
     private boolean basePrice;
     private boolean fixedPrice;
     private boolean producerPrice;
     private boolean customerPromo;
     private Date creDate;
     private String creId;
     private Date modDate;
     private String modId;

    public ContractPricingPriority() {
    }

    public ContractPricingPriority(CustomerContract customerContract, boolean basePrice, boolean fixedPrice, boolean producerPrice, boolean customerPromo, Date creDate, String creId, Date modDate, String modId) {
       this.customerContract = customerContract;
       this.basePrice = basePrice;
       this.fixedPrice = fixedPrice;
       this.producerPrice = producerPrice;
       this.customerPromo = customerPromo;
       this.creDate = creDate;
       this.creId = creId;
       this.modDate = modDate;
       this.modId = modId;
    }
   
    public BigDecimal getContractId() {
        return this.contractId;
    }
    
    public void setContractId(BigDecimal contractId) {
        this.contractId = contractId;
    }
    public CustomerContract getCustomerContract() {
        return this.customerContract;
    }
    
    public void setCustomerContract(CustomerContract customerContract) {
        this.customerContract = customerContract;
    }
    public boolean isBasePrice() {
        return this.basePrice;
    }
    
    public void setBasePrice(boolean basePrice) {
        this.basePrice = basePrice;
    }
    public boolean isFixedPrice() {
        return this.fixedPrice;
    }
    
    public void setFixedPrice(boolean fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
    public boolean isProducerPrice() {
        return this.producerPrice;
    }
    
    public void setProducerPrice(boolean producerPrice) {
        this.producerPrice = producerPrice;
    }
    public boolean isCustomerPromo() {
        return this.customerPromo;
    }
    
    public void setCustomerPromo(boolean customerPromo) {
        this.customerPromo = customerPromo;
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




}


