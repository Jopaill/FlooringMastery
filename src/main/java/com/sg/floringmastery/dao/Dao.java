/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jordash
 */
public class Dao {
    //reusable variables
    int orderNumber;
    String customerName;
    String state;
    BigDecimal taxRate;
    String productType;
    BigDecimal area;
    BigDecimal costPerSquareFoot;
    BigDecimal laborCostPerSquareFoot;
    BigDecimal materialCost;
    BigDecimal laborCost;
    BigDecimal tax;
    BigDecimal total;
    
    private String ORDERS_PREFIX="Orders/";
    public List<Order> retrieveAllOrderOfFile(String nameOfFile) 
            throws FileNotFoundException, IOException {
        String nameOfPath=ORDERS_PREFIX+nameOfFile+".txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader(nameOfPath)));
        List<String> linesOfFile=new ArrayList<>();
        sc.nextLine();
        while(sc.hasNextLine()){
            linesOfFile.add(sc.nextLine());
        }
        sc.close();
        return unmarshall(linesOfFile);
    }
    
    
    private List<Order> unmarshall(List<String> ls){
        List<Order> result = new ArrayList<>();
        for(String s :ls){
            result.add(unmarshall(s));
        }
        return result;
    }
    
    private Order unmarshall(String ls){
        Order ord=new Order();
        String[] sp = ls.split(",");
        //Converting the strings into the right types
        orderNumber =Integer.valueOf(sp[0]);
        customerName = sp[1];
        state= sp[2];
        taxRate =new  BigDecimal(sp[3]);
        productType= sp[4];
        area=new BigDecimal(sp[5]);
        costPerSquareFoot=new BigDecimal(sp[6]);
        laborCostPerSquareFoot=new BigDecimal(sp[7]);
        materialCost=new BigDecimal(sp[8]);
        laborCost=new BigDecimal(sp[9]);
        tax=new BigDecimal(sp[10]);
        total=new BigDecimal(sp[11]);
        
        //Creating an order with those attributes
        ord.setOrderNumber(orderNumber);
        ord.setCustomerName(customerName) ;
        ord.setState(state);
        ord.setTaxRate(taxRate) ;
        ord.setProductType(productType);
        ord.setArea(area);
        ord.setCostPerSquareFoot(costPerSquareFoot);
        ord.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        ord.setMaterialCost(materialCost);
        ord.setLaborCost(laborCost);
        ord.setTax(tax);
        ord.setTotal(total);
        
        return ord;
    }
    
}
