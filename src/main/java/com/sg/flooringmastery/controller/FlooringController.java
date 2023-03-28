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
                    editAnOrder();
                case 4: //Remove an order
                    removeAnOrder();
                    break;
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
        view.displayOrderSuccessfullyCreated(ord);
    }

    private void removeAnOrder() {
        LocalDate ld=view.getFutureDate();
        int orderNumber=view.getOrderNumber();
        boolean isSucessfullyFoundAndRemoved = service.removeOrder(ld,orderNumber);
        view.removeOrderSuccess(isSucessfullyFoundAndRemoved, orderNumber,ld);
    }

    private void editAnOrder() throws IOException {
        LocalDate ld=view.getFutureDate();
        int orderNumber=view.getOrderNumber();
        List<Order> orders=service.getOrdersForDate(ld);
        boolean found=false;
        Order changingOrder=null;
        for(Order order :orders){
            if(order.getOrderNumber()==orderNumber){
                changingOrder=order;
                found=true;
                break;
            }
        }
        if(!found){
            view.displayOrderNotFound(ld,orderNumber);
            return;
        }
        
        view.displayOrderFoundToBeEditted(changingOrder);
        //Customer name:
        String name;
        int choice=
            view.displayingCustomerNameAndAskChange(changingOrder.getCustomerName());
        if(choice==1){
            name=view.getName();
        }else{
            name=changingOrder.getCustomerName();
        }
        
        //State
        String shortState=changingOrder.getState();
        String longState=null;
        StateInfo state=null;
        List<StateInfo> states=service.getStateInfos();
        for(StateInfo stateInfo: states){
            if(stateInfo.getState().equals(shortState)){
                longState=stateInfo.getStateName();
                state=stateInfo;
                break;
            }
        }
        int choiceState=view.displayCurrentStateAndAskChange(longState);
        
        if(choiceState==1){
            int choiceNewState=
                view.displayStatesAndGetChoice(states);
            state=states.get(choiceNewState);
        }
        
        //Product
        String currentProduct=changingOrder.getProductType();
        int choiceProduct=view.displayCurrentProductAndAskChange(currentProduct);
        Product product=null;
        List<Product> products=service.getProducts();
        if(choiceProduct==1){
            int choiceNewProduct=
                view.displayProductsAndGetChoice(products);
            product=products.get(choiceNewProduct);
        }else{
            for(Product productInList:products){
                if(productInList.getProductType().equals(currentProduct)){
                    product=productInList;
                    break;
                }
            }
        }
        
        //area
        BigDecimal currentArea=changingOrder.getArea();
        int choiceArea=view.displayCurrentAreaAndAskChange(currentArea);
        BigDecimal area;
        if(choiceArea==1){
            area=view.getAreaWanted();
        }else{
            area=currentArea;
        }
        
        //We can now create the order
        Order ord = service.createNewOrder(
                ld, 
                name, 
                state,
                product,
                area);
        
        
        service.removeOrder(ld, orderNumber);
        view.displayOrderSuccessfully(ord);
    }
}
