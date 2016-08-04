/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import com.start.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


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
        ArrayList<Pick> userPickSelection = new ArrayList<>();
        String listInfo="";
        Scanner in = new Scanner(System.in);
        int i=0;
        String optionsPrompt = "#Options  ::\n"
                + "     This program uses integers to evaluate user input (specify list position starting at 1)\n"
                + "     -1  --  add new StockList object with desired picks\n"
                + "     -2 - deletes StockList object from your Portfolios\n"
                + "     0 - quits program";
        String invalidSelection = "Please enter an option from from the list selection:     \n";

        

        

        System.out.println("\nHello");
        
        //starts

            System.out.println(optionsPrompt);

        boolean valid=false;
       
        boolean finished = false;
        while(!finished){


            //TODO:: catch user error
            System.out.println(optionsPrompt);
            
//            System.out.println("Or enter the list number to view an existing list");

                    for(StockList aList : yourLists){
                        i++;
                        System.out.println("List "+i+")     "+aList.getListCategory());
                    }
                    i=0;
                    try{
                        //collect next int
                        userInput = in.nextInt();
                        //if list number selection
                        if(userInput>=1 && userInput<=yourLists.size()){
                            
                            //find index
                            int index = userInput-1;
                            
                            //get stockpicks from list
                            userPickSelection = yourLists.get(index).getPicks();
                            //print picks Ticker and price to console
                            for(Pick pick : userPickSelection){
                                i++;
                                System.out.print("\nPick "+i+")     "+pick.getTicker()+"       Price: "+pick.getSharePrice()+" "+pick.getStock().getCurrency()+"\n");
                            }
                            i=0;
                            try{
                                //prompt user
                                System.out.print("Enter the stock's line number to see more details:     ");
                                //collect input
                                userInput = in.nextInt();
                                //if selection from StockList
                                if(userInput>=1 && userInput<=userPickSelection.size()){
                                    index=userInput-1;
                                    System.out.print(userPickSelection.get(index).format());
                                }
                            }
                            catch(InputMismatchException | IndexOutOfBoundsException e){
                                System.err.println(invalidSelection);
                                in.nextLine();
                            }
                            
                        }
                        // if user wants to add list
                        if (userInput == -1){
                            changer.addNewList();
                            in.nextLine();
                        }
                        //if user wants to quit
                        else if(userInput == 0){
                            finished = true;
                        }
                    }
                    catch(InputMismatchException | IndexOutOfBoundsException e){
                      System.err.println(invalidSelection);
                      in.nextLine();
                    }
            
        }    
    }     
}    

        //TODO::     ask the user if they would like add or delete items from the list
        
        //TODO::     create delete function in StockListChanger
    
        //TODO::     ask user if they would like to navigate
        //TODO::     ask user if want to update
        //TODO::     details if time

        

        

//    private static void handleInput(String re){
//        Scanner in = new Scanner(System.in);
//                //add commands to list
//        ArrayList<String> commands = new ArrayList<>();
//        commands.add("add list");
//        commands.add("add pick");
//        commands.add("rm list");
//        commands.add("rm pick");
//        commands.add("view");
//        
//        //create changer to alter list contents
//        StockListChanger changer = new StockListChanger();
//        
//        
//        boolean valid = false;
//        while(!false){
//            while(re.equals("add list")){
//                changer.addNewList();
//            }
//            while(re.equals("rm list")){
//                System.out.println("Enter list to be removed:     ");
//                re = in.next();
//                changer.removeList(re);
//            }
//            while(re.equals("add pick")){
////                System.out.print("Enter your new Portforlio name:     ");
////                re = in.next();
////                changer.addToList(re);
//            }
//            while(re.equals("rm pick")){
//                
//            }
//            while(re.equals("view")){
//                
//                System.out.println("Please Enter List Number");
//                in.nextLine();
//                int place = in.nextInt();
//
//                
//                StockListParser parser = new StockListParser();
//                
//                place = place-1;
//
//                StockList userSelection = parser.getStockList(place);
//                
//                
//                if(userSelection == null){
//                    System.err.println("Try Again");
//                    continue;
//                }
//                else{
//
//                    ArrayList<Pick> userPickSelection = parser.getStockList(place).getPicks();
//                    int i = 0;
//                    for(Pick pick : userPickSelection){
//                        i++;
//                        System.out.println("\nPick "+i+")     "+pick.getTicker()+"       Price: "+pick.getSharePrice()+" "+pick.getStock().getCurrency());
//                    }
//
//            }
//            while(re.equals("help")){
//                
//            }
//               
//        System.out.println("'"+re+"' is not a valid command\n          enter -5 to see a list of commands.");        
//            
//        }
//    }
