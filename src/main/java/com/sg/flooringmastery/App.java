/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringController;
import java.io.IOException;

/**
 *
 * @author Jordash
 */
public class App {
    public static void main(String[] args) throws IOException{
        //System.out.println("Hello World");
        FlooringController ctrl=new FlooringController();
        ctrl.run();
    }
}
