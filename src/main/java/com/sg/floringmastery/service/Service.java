/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floringmastery.service;

import com.sg.flooringmastery.model.Order;
import com.sg.floringmastery.dao.Dao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jordash
 */
public class Service {
    Dao dao=new Dao();

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
    
}
