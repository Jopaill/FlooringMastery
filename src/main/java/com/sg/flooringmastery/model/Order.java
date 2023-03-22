/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Jordash
 */
public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private LocalDate dateOfTheOrder;

    public LocalDate getDateOfTheOrder() {
        return dateOfTheOrder;
    }

    public void setDateOfTheOrder(LocalDate dateOfTheOrder) {
        this.dateOfTheOrder = dateOfTheOrder;
    }


    public Order(int orderNumber, String customerName, String state, BigDecimal taxRate, 
            String productType, BigDecimal area, BigDecimal costPerSquareFoot, 
            BigDecimal laborCostPerSquareFoot, BigDecimal materialCost, 
            BigDecimal laborCost, BigDecimal tax, BigDecimal total, LocalDate ld) {
        this.orderNumber=orderNumber;
        this.customerName=customerName;
        this.state=state;
        this.taxRate=taxRate;
        this.productType=productType;
        this.area=area;
        this.costPerSquareFoot=costPerSquareFoot;
        this.laborCostPerSquareFoot=laborCostPerSquareFoot;
        this.materialCost=materialCost;
        this.laborCost=laborCost;
        this.tax=tax;
        this.total=total;
        this.dateOfTheOrder=ld;
    }
    
    public Order(){
    }
    
    
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }
    
    @Override
    public String toString(){
        return orderNumber+","+
                customerName+","+
                state+","+
                taxRate+","+
                productType+","+
                area+","+
                costPerSquareFoot+","+
                laborCostPerSquareFoot+","+
                materialCost+","+
                laborCost+","+
                tax+","+
                total;
    }
}
