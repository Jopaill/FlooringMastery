/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.StateInfo;
import com.sg.flooringmastery.ui.View;
import com.sg.flooringmastery.service.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jordash
 */
public class FlooringController {
    private View view= new View();
    private Service service=new Service();
    private LocalDate ld;
    
    
    public void run() throws IOException {
        view.displayWelcomeBanner();
        
        while(true) {
            int choice = view.displayMenuAndGetChoice();
            
            switch(choice) {
                case 1: 
                    displayOrders();
                    break;
                case 2: //view one
                    addingNewOrder();
                    break;
                case 3: //add
                    //Book newBook = view.getNewBook();
                    //service.addBook(newBook);
                    //view.displayAddSuccess();
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    //break;
                case 4: //update
                    //String updateTitle = view.getBookTitleToUpdate();
                    //Book updateBook = service.getBookByTitle(updateTitle);
                    //if(updateBook != null) {
                    //    updateBook = view.getUpdateBook(updateBook);
                    //    service.updateBook(updateBook);
                    //    view.displayUpdateSuccess();
                    //} else {
                    //    view.displayError("Book doesn't exist");
                    //}
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    //break;
                case 5: //delete
//                    String deleteTitle = view.getBookTitleToDelete();
//                    Book deleteBook = service.getBookByTitle(deleteTitle);
//                    if(deleteBook != null) {
//                        service.deleteBookByTitle(deleteTitle);
//                        view.displayDeleteSuccess();
//                    } else {
//                        view.displayError("Book doesn't exist");
//                    }
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    //break;
                case 6: //exit
                    view.displayExit();
                    System.exit(0);
                    break;
                default: //unknown
                    view.displayError("Unknown Option");
                    break;
            }
        }
        
        

    }
    //Case 1: Displaying orders for a certain date
    private void displayOrders() throws IOException{
        ld=view.displayOrders();
        List<Order> orders=service.getOrdersForDate(ld);
        view.displayOrders(orders);
    }
    
    //Case 2: Adding a new order to the system
    private void addingNewOrder() throws IOException{
        ld=view.getFutureDate();
        String name =view.getName();
        //I would need to get the valid states
        //Display them to the user and ask to choose from those states
        List<StateInfo> states = service.getStateInfos();
        int choiceOfState=view.displayStatesAndGetChoice(states);
        //System.out.println("State choosen is "+states.get(choiceOfState).getStateName());
        //Do the same with the product type
        List<Product> products = service.getProducts();
        int choiceOfProduct = view.displayProductsAndGetChoice(products);
        //System.out.println("Product choosen is "+products.get(choiceOfProduct).getProductType());
        BigDecimal area=view.getAreaWanted();
        
        //We can now create the order
        Order ord = service.createNewOrder(
                ld, 
                name, 
                states.get(choiceOfState),
                products.get(choiceOfProduct),
                area);
        
    }
    
    
    
    
    
}
