/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.math.BigDecimal;
import java.util.ArrayList;
import yahoofinance.Stock;
/**
 *Describes Weekly Stock Picks
 * @author corykleiser
 */
public class StockList {
    public String pickCategory; 
    public ArrayList<Pick> picks;
    
/**
 * Constructs StockList and Category/Name for your picks i.e. bulls/bear/week/month etc
 * @param cat Enter List Name
 */    
    public StockList(String cat) {
        picks = new ArrayList<>();
        pickCategory = cat;
    }
    
    /**
     * Instantiates Pick object and ties to StockList object
     * @param ticker
     * @param rank 
     */
    public void addPick(String ticker, int rank){
        Pick aStock = new Pick(ticker, rank);
        picks.add(aStock);
    }
    /**
     * finds list name/category
     * @return 
     */
    public String getListCategory(){
        return pickCategory;
    }
    /**
     * finds stock picks on list
     * @return 
     */
    public ArrayList<Pick> getPicks(){
        return picks;
    }
    
    /**
     * Finds more specific details for user
     * @param i place in Pick array
     * @return specific details
     */
    public String getData(int i){
        //TODO: set up which data to pull and return
        Pick stock = picks.get(i);
        String d = stock.format();
        return d;
    }
    
    /**
     * formats stock data for console tests
     * @return 
     */
    public String format(){
        String r = "    " + pickCategory + "    Weekly Picks\n\n";
        for(Pick stock : picks){
            
            Stock stockData = stock.getStock();
                
            String name = stock.getCompanyName();
            String ticker = stock.getTicker();
            BigDecimal price = stock.getSharePrice();
            BigDecimal change = stockData.getQuote().getChange();
            BigDecimal percentChange = stockData.getQuote().getChangeInPercent();
            
            r = r + name + " has a current price of " + price
                    +".  "+ ticker + " has moved " + change 
                    + " today, or " + percentChange + "%.\n\n";
        }
        return r;
    }
}
