/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 *
 * @author corykleiser
 */
public class Pick {
    public Stock stock;
    public String companyName;
    public BigDecimal sharePrice;
    public String tik;
    public int listRank;
    java.net.URL dataUrl;

    /**
     * 
     * @param ticker Stock ticker symbol
     * @param rank Rank in List
     */
    public Pick(String ticker, int rank){
        try {
            listRank = rank;
            tik = ticker;
            stock = YahooFinance.get(tik);
            companyName = stock.getName();
            sharePrice = stock.getQuote().getPrice();
        } catch (IOException ex) {
            System.out.println("Requested Stock Not Found");
            System.out.println(ex);
        }
    }
    
    public Stock getStock(){
        return stock;
    }
    
    /**
     * returns rank
     * @return 
     */
    public int getListRank(){
        return listRank;
    }   
    /**
     * returns Ticker
     * @return 
     */
    public String getTicker(){
        return tik;
    }
    /**
     * returns Share Price
     * @return 
     */
    public BigDecimal getSharePrice(){
        try {
        sharePrice = stock.getQuote(true).getPrice();
        } catch (IOException ex) {
            
            System.out.println("Error: Stock Price Not Found");
            System.out.println(ex);

        }
        return sharePrice;
    }

    public String getCompanyName(){
        return companyName;
    }
    
    public String format(){
                
            String name = stock.getName();
            BigDecimal price = stock.getQuote().getPrice();
            String lastTrade = stock.getQuote().getLastTradeTimeStr();
            BigDecimal dayHigh = stock.getQuote().getDayHigh();
            BigDecimal dayLow = stock.getQuote().getDayLow();
            BigDecimal change = stock.getQuote().getChange();
            BigDecimal percentChange = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            Calendar dividendPayDate = stock.getDividend().getPayDate();
            
            BigDecimal daySpread;

            String d = name + "\n"
                    + "     mark:          " + price + "\n"
                    + "     change:        " + change + " or " +percentChange + "%\n"
                    + "     Last Exchange: " + lastTrade + "\n"
                    + "     spread:        " + dayLow + " - " + dayHigh + "\n"
                    + "     PEG ratio:     " + peg + "\n"
                    + "     Dividend:      " + dividend + "\n\n\n\n\n\n\n";
            return d;
    }
        
      
}
