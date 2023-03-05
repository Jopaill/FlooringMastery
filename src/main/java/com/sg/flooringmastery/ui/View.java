/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.StateInfo;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jordash
 */
public class View {
    private final String BANNER_EQUALS=" ================== ";
    private final String BANNER_MINUS=" --------- ";
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
    LocalDate ld;
    
    
    
    UserIO io=new UserIOConsoleImpl();
    public void displayWelcomeBanner() {
        String message=addingEquals("Welcome to the Flooring Mastery");
        io.print(message);
    }
    
    public int displayMenuAndGetChoice() {
        io.print("");
        io.print("Main Menu");
        io.print("1. View Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Exit");
        
        return io.readInt("Please select an option:", 1, 6);
    }
    
    public LocalDate getDate(){
        int year;
        int month;
        int day;
        while(true){
            year=io.readInt("Year:");
            month=io.readInt("Month:");
            day=io.readInt("Day");
            try{
                ld = LocalDate.of( year , month , day );
                break;
            }catch(DateTimeException e){
                io.print("The date "+year+"-"+month+"-"+day+" is not valid");
                io.print("Please try again");
            }
        }
        io.print("The date "+year+"-"+month+"-"+day+" was entered successfully.");
        return ld;
    }
    
    public void displayExit() {
        io.print("Exiting the Flooring Mastery");
    }

    public void displayError(String unknown_Option) {
        throw new UnsupportedOperationException("This should not have happened"); //To change body of generated methods, choose Tools | Templates.
    }

    public LocalDate displayOrders() {
        //Display Orders banner
        io.print(addingEquals("1. Display Orders"));
        io.print("Enter a valid date");
        return getDate();
    }
    private String addingEquals(String s){
        return BANNER_EQUALS + s+ BANNER_EQUALS;
        
    }

    public void displayOrders(List<Order> orders) {
        int i=1;
        for(Order ord: orders){
            displayOrder(ord, i);
            i++;
        }
    }
    private void displayOrder(Order ord, int i){
        orderNumber=ord.getOrderNumber();
        customerName=ord.getCustomerName();
        state=ord.getState();
        taxRate=ord.getTaxRate();
        productType=ord.getProductType();
        area=ord.getArea();
        costPerSquareFoot=ord.getCostPerSquareFoot();
        laborCostPerSquareFoot=ord.getLaborCostPerSquareFoot();
        materialCost=ord.getMaterialCost();
        laborCost=ord.getLaborCost();
        tax=ord.getTax();
        total=ord.getTotal();
        
        io.print(BANNER_MINUS + "Order # "+i+BANNER_MINUS);
        io.print(withSpaces("Order Number:")+ord.getOrderNumber());
        io.print(withSpaces("Customer Name:")+ord.getCustomerName());
        io.print(withSpaces("State:")+ord.getState());
        io.print(withSpaces("Tax Rate:")+ord.getTaxRate());
        io.print(withSpaces("Product Type:")+ord.getProductType());
        io.print(withSpaces("Area:")+ord.getArea());
        io.print(withSpaces("Cost Per Square Foot:")+ord.getCostPerSquareFoot());
        io.print(withSpaces("Labor Cost Per Square Foot:")+ord.getLaborCostPerSquareFoot());
        io.print(withSpaces("Material Cost:")+ord.getMaterialCost());
        io.print(withSpaces("Labor Cost:")+ord.getLaborCost());
        io.print(withSpaces("Tax:")+ord.getTax());
        io.print(withSpaces("Total:")+ord.getTotal());
    }
    private String withSpaces(String s){
        while(s.length()<28){
            s=s+" ";
        }
        return s;
    }

    public String getName() {
        String name="";
        
        while(true){
            name=io.readString("Enter your name");
            if(nameIsValid(name)){
                break;
            }
            io.print("Make sure your name only contains [a,...,z] or [A,...,Z] or ',' or '.'");
        }
        return name;
    }

    private boolean nameIsValid(String name) {
        char currentChar;
        if(name.length()==0){
            return false;
        }
        for(int i=0;i<name.length();i++){
            currentChar=name.charAt(i);
            if(!charIsValid(currentChar)){
                return false;
            }
        }
        return true;
    }
    private boolean charIsValid(char c){
        return 
                Character.isDigit(c) ||
                Character.isAlphabetic(c)||
                c==',' ||
                c=='.' ||
                c==' ';
    }

    public LocalDate getFutureDate() {
        ld=getDate();
        while(ld.isBefore(LocalDate.now())){
            io.print("However, the date entered is in the past. Try again");
            ld=getDate();
        }
        return ld;
    }

    public int displayStatesAndGetChoice(List<StateInfo> states) {
        io.print("Which state do you live in?");
        for(int i=0;i<states.size();i++){
           io.print((i+1)+"    -    "+states.get(i).getStateName());
        }
        int choice=io.readInt("Simply enter the number", 1, states.size());
        return choice-1;
    }

    public int displayProductsAndGetChoice(List<Product> products) {
        io.print("Which product do you want?");
        for(int i=0;i<products.size();i++){
            Product cur = products.get(i);
            io.print((i+1)+"    -    "+cur.getProductType()+"("+cur.getCostPerSquareFoot()+" $/sq foot )");
        }
        int choice=io.readInt("Simply enter the number", 1, products.size());
        return choice-1;
    }

    public BigDecimal getAreaWanted() {
        while(true){
            try{
                String areaString=io.readString("Enter an area of at least 100 sq foot");
                BigDecimal areaBigD=new BigDecimal(areaString);
                return areaBigD;
            }catch(Exception e){
                io.print("The area entered is not right. Try again");
            }
            
        }
        
        
        
    }
}
