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
 * @author moku
 */
public class StockListParser {
    private DocumentBuilder builder;
    private XPath path;

   /**
      Constructs a parser that can parse item lists.
   */
    public StockListParser() 
        throws ParserConfigurationException
    {
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        builder = dbfactory.newDocumentBuilder();
        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();
    }

    /**
      Parses an XML file containing an item list.
      @param fileName the name of the file
      @return an array list containing all items in the XML file
    */
    public ArrayList<StockList> parse(String fileName) {
        float lastPriceFound;
        try{
            File f = new File(fileName);
            Document doc = builder.parse(f);
            ArrayList<StockList> stockLists = new ArrayList<>();
            int listCount = Integer.parseInt(path.evaluate("count(/lists/list)", doc));
            System.out.println("test listCount:" + listCount);
            for (int i = 1; i <= listCount; i++){

                String listCat = path.evaluate("/lists/list[" + i + "]/cat", doc);
                System.out.println("test listCat: "+listCat);
                String ticker = path.evaluate("/lists/list[" + i +"]/pick[1]/ticker", doc);
                System.out.println(ticker);
                
                StockList list = new StockList(listCat);

                int pickCount = Integer.parseInt(path.evaluate("count(/lists/list["+i+"]/pick)", doc));
                System.out.println("test pickCount: "+pickCount);

                
                for (int j = 1; j <= pickCount; j++){
                    ticker = path.evaluate("/lists/list[" + i +"]/pick["+j+"]/ticker", doc);
                    System.out.println("test ticker: "+ticker);
                    int rank = Integer.parseInt(path.evaluate("/lists/list[" + i + "]/pick[" + j + "]/rank", doc));
                    System.out.println("test   " + rank);
                    lastPriceFound = Float.parseFloat(path.evaluate("/lists/list/pick[" + j + "]/lastPriceFound", doc));
                    list.addPick(ticker, rank);
                    System.out.println("test: " + list);
                }
                stockLists.add(list);
            }
            return stockLists;
        }
        catch(IOException | XPathExpressionException | SAXException e){
            System.err.println(e);
            return null;
        }
    }
}

