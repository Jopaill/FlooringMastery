/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.dao.Dao;
import com.sg.flooringmastery.dao.DaoImpl;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.StateInfo;
import java.io.IOException;
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_EVEN;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jordash
 */
public class Service {
    Dao dao=new DaoImpl();
    private final static MathContext mc = new MathContext(4);
    BigDecimal ONE_HUNDRED=new BigDecimal("100");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMddyyyy");

    public void getOrdersForDate(int year, int month, int day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Order> getOrdersForDate(LocalDate ld) throws IOException {
        List<Order> orders=new ArrayList<>();
        String year=""+ ld.getYear();
        String month=returningDayOrMonthWithZero(ld.getMonthValue());
        String day=returningDayOrMonthWithZero(ld.getDayOfMonth());
        String nameOfFile="Orders_"+month+day+year;
        return dao.retrieveAllOrderOfFile(nameOfFile); //List<order>
    }
    public String returningDayOrMonthWithZero(int i){
        if(i<10)
            return "0"+i;
        else
            return ""+i;
    }

    public List<StateInfo> getStateInfos() throws IOException {
        return dao.getStateInfos();
    }

    public List<Product> getProducts() throws IOException {
        return dao.getProducts();
    }

    public Order createNewOrder(LocalDate ld, String customerName, StateInfo stateInfo, Product product, BigDecimal area) {
        int orderNumber = dao.getNeworderNumber();
        //customerName we have
        String state=stateInfo.getState();
        BigDecimal taxRate=new BigDecimal(stateInfo.getTaxRate().toString());
        String productType=product.getProductType();
        // BigDecimal area
        BigDecimal costPerSquareFoot=product.getCostPerSquareFoot();
        BigDecimal laborCostPerSquareFoot=product.getLaborCostPerSquareFoot();
        BigDecimal materialCost=area.multiply(costPerSquareFoot)
                .setScale(2, ROUND_HALF_EVEN);
        BigDecimal laborCost=area.multiply(laborCostPerSquareFoot)
                .setScale(2, ROUND_HALF_EVEN);
        BigDecimal temp=materialCost.add(laborCost);
        BigDecimal tax=temp
                .multiply(
                        taxRate.divide(ONE_HUNDRED))
                .setScale(2, ROUND_HALF_EVEN);
        BigDecimal total = temp.add(tax);
        
        Order order = new Order(
                    orderNumber,
                    customerName,
                    state,
                    taxRate,
                    productType,
                    area,
                    costPerSquareFoot,
                    laborCostPerSquareFoot,
                    materialCost,
                    laborCost,
                    tax,
                    total,
                    ld
                            );
        dao.addNewOrderToStorage(order);
        return order;
    }

    public boolean removeOrder(LocalDate ld, int orderNumber) {
        List<Order> orders;
        try{
            orders = dao.retrieveAllOrderOfFile("Orders_"+ld.format(dateTimeFormatter));
        }catch(IOException e){
            return false;
        }
        dao.removeAllOrdersForDay("Orders_"+ld.format(dateTimeFormatter));
        boolean flagOrderFound=false;
        for(Order order : orders){
            if(order.getOrderNumber()==orderNumber){
                flagOrderFound=true;
                continue;
            }else{
                order.setDateOfTheOrder(ld);
                dao.addNewOrderToStorage(order);
            }
        }
        return true;      
    }
    
    
}
