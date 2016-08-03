/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.start.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;


/**
 *
 * @author corykleiser
 */
public class StockTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<StockList> yourLists = null;
        
        
        StockListParser parser = new StockListParser();
        yourLists = parser.parse("src/com/test/itemsEX.xml");

        //initial categories
        System.out.println(parser.listCategories(yourLists));

        
        // TODO: code application logic here
        System.out.println("Hello");
        
        
        
        
            StockListChanger changer = new StockListChanger();
            changer.addNewList();
        
        //updated categories
        System.out.println(parser.listCategories(yourLists));
        
        
        
//        String pullStockData = "http://finance.yahoo.com/d/quotes.csv?sTIK=&f=snd1l1yr";
    }
}
