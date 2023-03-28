/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.StateInfo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jordash
 */
public interface Dao {
    public List<Order> retrieveAllOrderOfFile(String nameOfFile)
            throws FileNotFoundException, IOException;
    
    public List<StateInfo> getStateInfos()
            throws FileNotFoundException, IOException ;
    
    public List<Product> getProducts()
            throws FileNotFoundException, IOException ;

    public int getNeworderNumber();

    public void addNewOrderToStorage(Order order);

    public void removeAllOrdersForDay(String string);

    public void exportAllData();
}
