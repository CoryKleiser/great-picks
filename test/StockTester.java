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
        ArrayList<StockList> yourLists;
        StockListParser parser = new StockListParser();  
        yourLists = parser.parse("src/com/test/itemsEX.xml");
        StockListChanger changer = new StockListChanger();
        int userInput;
        ArrayList<Pick> userPickSelection;
        Scanner in = new Scanner(System.in);
        int i=0;
        


        
        for(StockList aList : yourLists){
            i++;
            System.out.println("List "+i+")     "+aList.getListCategory());
        }
        i=0;
        System.out.println("\nHello");
        
        System.out.print("   Options::\n add = adds new StockList with desired picks."
                + "\nEnter the number of the list you would like to view:    ");
        
        //TODO:: catch user error
        userInput = in.nextInt() - 1 ;
        userPickSelection = yourLists.get(userInput).getPicks();
        for(Pick pick : userPickSelection){
            i++;
            System.out.print("\nPick "+i+")     "+pick.getTicker()+"       Price: "+pick.getSharePrice());
        }

        //TODO::     ask the user if they would like add or delete items from the list
        //TODO::     create delete function in StockListChanger
        //TODO::     ask user if they would like to navigate
        //TODO::     ask user if want to update
        //TODO::     details if time

        

        
        
        
        
            changer.addNewList();
        
        //updated categories
        System.out.println(parser.listCategories());
        
        
        
//        String pullStockData = "http://finance.yahoo.com/d/quotes.csv?sTIK=&f=snd1l1yr";
    }
}
