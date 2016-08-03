/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
/**
 *Describes Weekly Stock Picks
 * @author corykleiser
 */
public class StockList {
    public String pickCategory; 
    public ArrayList<Pick> picks;
    
/**
 * Creates a Category for your picks i.e. bulls/bear/week/month etc
 * @param cat Enter List Name
 */    
    public StockList(String cat) {
        picks = new ArrayList<>();
        pickCategory = cat;
    }
    
    public void addPick(String ticker, int rank){
        Pick aStock = new Pick(ticker, rank);
        picks.add(aStock);
    }
    
    public String getData(int i){
        Pick stock = picks.get(i);
        String d = stock.format();
        return d;
    }
    
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
