/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.StateInfo;
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
public class DaoImpl implements Dao{
    //reusable variables
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
        return unmarshallOrders(linesOfFile);
    }
    
    
    private List<Order> unmarshallOrders(List<String> ls){
        List<Order> result = new ArrayList<>();
        for(String s :ls){
            result.add(unmarshallOrder(s));
        }
        return result;
    }
    
    private Order unmarshallOrder(String ls){
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
    private String STATES_FILE="Data/Taxes.txt";
    public List<StateInfo> getStateInfos()
            throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(STATES_FILE)));
        List<String> linesOfFile=new ArrayList<>();
        sc.nextLine();
        while(sc.hasNextLine()){
            linesOfFile.add(sc.nextLine());
        }
        sc.close();
        return unmarshallStates(linesOfFile);
    }
    
    private List<StateInfo> unmarshallStates(List<String> statesInfoString){
        List<StateInfo> statesInfoObjects = new ArrayList<StateInfo>();
        for(String stateInfoString: statesInfoString){
            statesInfoObjects.add(unmarshallState(stateInfoString));
        }
        return statesInfoObjects;
    }
    
    private StateInfo unmarshallState(String state){
        String[] stateStringSpli = state.split(",");
        StateInfo stateInfo=new StateInfo();
        stateInfo.setState(stateStringSpli[0]);
        stateInfo.setStateName(stateStringSpli[1]);
        stateInfo.setTaxRate(new BigDecimal(stateStringSpli[2]));
        return stateInfo;
    }
    
    private String PRODUCTS_FILE="Data/Products.txt";
    public List<Product> getProducts() 
            throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        List<String> linesOfFile=new ArrayList<>();
        sc.nextLine();
        while(sc.hasNextLine()){
            linesOfFile.add(sc.nextLine());
        }
        sc.close();
        return unmarshallProducts(linesOfFile);
    }

    private List<Product> unmarshallProducts(List<String> linesOfFile) {
        List<Product> products = new ArrayList<>();
        for(String st:linesOfFile){
            products.add(unmarshallProduct(st));
        }
        return products;
    }

    private Product unmarshallProduct(String st) {
        String[] productSplitted = st.split(",");
        Product product = new Product();
        product.setProductType(productSplitted[0]);
        product.setCostPerSquareFoot(new BigDecimal(productSplitted[1]));
        product.setLaborCostPerSquareFoot(new BigDecimal(productSplitted[2]));
        return product;
    }
    
}
