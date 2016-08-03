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
        Scanner in = new Scanner(System.in);
        ArrayList<StockList> yourLists;
        
        
        //TODO Parse XML file here
//        try {
//            StockListParser parser = new StockListParser();
//            yourLists = parser.parse("src/com/test/itemsEX.xml");
//            
//            System.out.println(yourLists);
//            yourLists.stream().forEach((aList) -> {
//                System.out.print(aList.pickCategory);
//            });
//        }
//        catch (ParserConfigurationException e){
//            System.err.println(e);
//        }
        
        // TODO: code application logic here
        System.out.println("Hello");
        
        
        
        
            StockListChanger changer = new StockListChanger();
            changer.addNewList();
        
        
        
        
        
//        
//        StockList test = new StockList("Bulls of the Week!");
//        
//        test.addPick("TSLA", 1);
//        test.addPick("SOL", 2);
//        test.addPick("CIDM", 3);
//        test.addPick("GOOGL", 1);
//        test.addPick("AAPL", 10);
//        
//        System.out.println(test.getData(1));
//        
//        System.out.println(test.format());
//        
//        String pullStockData = "http://finance.yahoo.com/d/quotes.csv?sTIK=&f=snd1l1yr";
    }
}
