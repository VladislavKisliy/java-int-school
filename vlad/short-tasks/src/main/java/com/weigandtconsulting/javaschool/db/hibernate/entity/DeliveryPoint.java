package com.weigandtconsulting.javaschool.db.hibernate.entity;
// Generated Oct 28, 2015 4:51:35 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DeliveryPoint generated by hbm2java
 */
public class DeliveryPoint  implements java.io.Serializable {


     private DeliveryPointId id;
     private CustomerMaster customerMaster;
     private DeliveryPoint deliveryPoint;
     private SalesAgent salesAgent;
     private String name;
     private Long channel;
     private Long subchannel;
     private Long subchannelType;
     private Long dpGroup;
     private Long x;
     private Long y;
     private String invoicingLevel;
     private boolean notDeliverableInd;
     private short vatRegion;
     private Date creDate;
     private String creId;
     private Date modDate;
     private String modId;
     private Date delDate;
     private String delId;
     private Long timeframe;
     private BigDecimal id_1;
     private Set customerAddrs = new HashSet(0);
     private Set deliveryPoints = new HashSet(0);

    public DeliveryPoint() {
    }

	
    public DeliveryPoint(DeliveryPointId id, CustomerMaster customerMaster, SalesAgent salesAgent, String name, String invoicingLevel, boolean notDeliverableInd, short vatRegion, Date creDate, String creId, Date modDate, String modId, BigDecimal id_1) {
        this.id = id;
        this.customerMaster = customerMaster;
        this.salesAgent = salesAgent;
        this.name = name;
        this.invoicingLevel = invoicingLevel;
        this.notDeliverableInd = notDeliverableInd;
        this.vatRegion = vatRegion;
        this.creDate = creDate;
        this.creId = creId;
        this.modDate = modDate;
        this.modId = modId;
        this.id_1 = id_1;
    }
    public DeliveryPoint(DeliveryPointId id, CustomerMaster customerMaster, DeliveryPoint deliveryPoint, SalesAgent salesAgent, String name, Long channel, Long subchannel, Long subchannelType, Long dpGroup, Long x, Long y, String invoicingLevel, boolean notDeliverableInd, short vatRegion, Date creDate, String creId, Date modDate, String modId, Date delDate, String delId, Long timeframe, BigDecimal id_1, Set customerAddrs, Set deliveryPoints) {
       this.id = id;
       this.customerMaster = customerMaster;
       this.deliveryPoint = deliveryPoint;
       this.salesAgent = salesAgent;
       this.name = name;
       this.channel = channel;
       this.subchannel = subchannel;
       this.subchannelType = subchannelType;
       this.dpGroup = dpGroup;
       this.x = x;
       this.y = y;
       this.invoicingLevel = invoicingLevel;
       this.notDeliverableInd = notDeliverableInd;
       this.vatRegion = vatRegion;
       this.creDate = creDate;
       this.creId = creId;
       this.modDate = modDate;
       this.modId = modId;
       this.delDate = delDate;
       this.delId = delId;
       this.timeframe = timeframe;
       this.id_1 = id_1;
       this.customerAddrs = customerAddrs;
       this.deliveryPoints = deliveryPoints;
    }
   
    public DeliveryPointId getId() {
        return this.id;
    }
    
    public void setId(DeliveryPointId id) {
        this.id = id;
    }
    public CustomerMaster getCustomerMaster() {
        return this.customerMaster;
    }
    
    public void setCustomerMaster(CustomerMaster customerMaster) {
        this.customerMaster = customerMaster;
    }
    public DeliveryPoint getDeliveryPoint() {
        return this.deliveryPoint;
    }
    
    public void setDeliveryPoint(DeliveryPoint deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }
    public SalesAgent getSalesAgent() {
        return this.salesAgent;
    }
    
    public void setSalesAgent(SalesAgent salesAgent) {
        this.salesAgent = salesAgent;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Long getChannel() {
        return this.channel;
    }
    
    public void setChannel(Long channel) {
        this.channel = channel;
    }
    public Long getSubchannel() {
        return this.subchannel;
    }
    
    public void setSubchannel(Long subchannel) {
        this.subchannel = subchannel;
    }
    public Long getSubchannelType() {
        return this.subchannelType;
    }
    
    public void setSubchannelType(Long subchannelType) {
        this.subchannelType = subchannelType;
    }
    public Long getDpGroup() {
        return this.dpGroup;
    }
    
    public void setDpGroup(Long dpGroup) {
        this.dpGroup = dpGroup;
    }
    public Long getX() {
        return this.x;
    }
    
    public void setX(Long x) {
        this.x = x;
    }
    public Long getY() {
        return this.y;
    }
    
    public void setY(Long y) {
        this.y = y;
    }
    public String getInvoicingLevel() {
        return this.invoicingLevel;
    }
    
    public void setInvoicingLevel(String invoicingLevel) {
        this.invoicingLevel = invoicingLevel;
    }
    public boolean isNotDeliverableInd() {
        return this.notDeliverableInd;
    }
    
    public void setNotDeliverableInd(boolean notDeliverableInd) {
        this.notDeliverableInd = notDeliverableInd;
    }
    public short getVatRegion() {
        return this.vatRegion;
    }
    
    public void setVatRegion(short vatRegion) {
        this.vatRegion = vatRegion;
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
    public Date getDelDate() {
        return this.delDate;
    }
    
    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }
    public String getDelId() {
        return this.delId;
    }
    
    public void setDelId(String delId) {
        this.delId = delId;
    }
    public Long getTimeframe() {
        return this.timeframe;
    }
    
    public void setTimeframe(Long timeframe) {
        this.timeframe = timeframe;
    }
    public BigDecimal getId_1() {
        return this.id_1;
    }
    
    public void setId_1(BigDecimal id_1) {
        this.id_1 = id_1;
    }
    public Set getCustomerAddrs() {
        return this.customerAddrs;
    }
    
    public void setCustomerAddrs(Set customerAddrs) {
        this.customerAddrs = customerAddrs;
    }
    public Set getDeliveryPoints() {
        return this.deliveryPoints;
    }
    
    public void setDeliveryPoints(Set deliveryPoints) {
        this.deliveryPoints = deliveryPoints;
    }




}


