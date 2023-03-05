/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.ui.View;
import com.sg.floringmastery.service.Service;
import java.io.IOException;
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
                case 1: //view all
                    ld=view.displayOrders();
                    List<Order> orders=service.getOrdersForDate(ld);
                    view.displayOrders(orders);
                    break;
                case 2: //view one
                    ld=view.getFutureDate();
                    String name =view.getName();
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    //break;
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
    
}
