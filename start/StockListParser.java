/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *parser of XML data
 * @author corykleiser
 */
public class StockListParser {
    private DocumentBuilder builder;
    private XPath path;
    ArrayList<StockList> stockLists = new ArrayList<>();


   /**
      Constructs a parser that can parse item lists.
   */
    public StockListParser() {
        try{
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            builder = dbfactory.newDocumentBuilder();
            XPathFactory xpfactory = XPathFactory.newInstance();
            path = xpfactory.newXPath();
        }
        catch(ParserConfigurationException e){
            System.err.println(e + "\nError occured while building parser");
        }
    }

    /**
      Parses an XML file containing an item list.
      @param fileName the name of the file
      @return an array list containing all items in the XML file
    */
    public ArrayList<StockList> parse(String fileName) {
        //var for price updates
        float lastUpdatedPrice;

        //try to parse file
        try{
            File f = new File(fileName);
            Document doc = builder.parse(f);
            
            //variable to hold all StockList objects
            
            //Finds amount of lists in data file
            int listCount = Integer.parseInt(path.evaluate("count(/lists/list)", doc));
                        
            //cycle through lists and instantiate StockList objects for each
            for (int i = 1; i <= listCount; i++){

                //find list category from data file
                String listCat = path.evaluate("/lists/list[" + i + "]/cat", doc);
                //test out list category
                
                //Instantiate StockList
                StockList list = new StockList(listCat);

                //find pick count
                int pickCount = Integer.parseInt(path.evaluate("count(/lists/list["+i+"]/pick)", doc));
                //test out pick count
                //System.out.println("test pickCount: "+pickCount);

                //cycle through picks and instantiate Pick objects for each
                for (int j = 1; j <= pickCount; j++){
                    
                    //find ticker
                    String ticker = path.evaluate("/lists/list[" + i +"]/pick["+j+"]/ticker", doc);
                    //test out ticker
                    //System.out.println("test ticker: "+ticker);
                    
                    //find rank
                    int rank = Integer.parseInt(path.evaluate("/lists/list[" + i + "]/pick[" + j + "]/rank", doc));
                    //test out rank
                    //System.out.println("test   " + rank);
                    
                    //get Last updated price
                    lastUpdatedPrice = Float.parseFloat(path.evaluate("/lists/list/pick[" + j + "]/lastUpdatedPrice", doc));
                    
                    //instantiate Pick object
                    list.addPick(ticker, rank);
                }
                //add finished StockList to list
                stockLists.add(list);
            }
            // return array of StockList objects
            return stockLists;
        }
        catch(IOException | XPathExpressionException | SAXException | NumberFormatException e){
            System.err.println(e);
            return null;
        }
    }
    public StockList getStockList(int i){
        return stockLists.get(i);
    }
    public ArrayList<StockList> getStockLists(){
        return stockLists;
    }
    public ArrayList<String> listCategories(){
        try{
            //instantiates categories variable for display
            ArrayList<String> categories = new ArrayList<String>();

            //Finds amount of lists in data file
            int cycleCount = stockLists.size();
            //gets individual categories
            for (int i=0; i < cycleCount; i++){
                //find cat
                String cat = stockLists.get(i).getListCategory();
                //add cat to list
                categories.add(cat);
            }

            return categories;
    }
        catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
}

