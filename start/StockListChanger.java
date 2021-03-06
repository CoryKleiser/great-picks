/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;


/**
 *
 * @author corykleiser
 */
public class StockListChanger {
    private DocumentBuilder builder;
    private Document doc;
    private XPath path;
    Scanner in = new Scanner(System.in);


    /**
     * Lets user add a new list to their portfolio
     */
    public void addNewList(){
            
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            builder = documentBuilderFactory.newDocumentBuilder();
            doc = builder.parse("src/com/test/itemsEX.xml");
            Element root = doc.getDocumentElement();

        
        
            Element list = doc.createElement("list");
            System.out.print("Please enter a title or category for the new list:   ");
            String category = in.nextLine();
            Element cat = doc.createElement("cat");
            cat.appendChild(doc.createTextNode(category));
            list.appendChild(cat);

            StockList newList = new StockList(category);
            
            boolean edit = true;
            while (edit){
                
                //instantiate ticker;
                String ticker="";
                boolean t = false;
                while(!t){
                    System.out.print("Please enter the ticker of the stock you would like to add:   ");
                    ticker = in.next();
                        //does the entry exist
                         BigDecimal price = YahooFinance.get(ticker).getQuote().getPrice();
                        
                        if(price!=null){
                            Stock stockPicked = YahooFinance.get(ticker);
                            t=true;
                        }
                        else{
                            System.err.println("\n     !!ERROR:: No Match!");
                            System.out.println("Ah, Ah... I don't recognize this ticker\n\n");
                        }
                }
                int rank = 0;
                        //Error Handler
                boolean valid = false;
                while (!valid) {
                    try {

                        System.out.print("Please enter a rank you would like to associate with the stock:   ");
                        rank = in.nextInt();

                        valid = true;
                    }
                    catch(InputMismatchException e) {
                        System.err.println("\n!!INVALID INPUT::   Not a Number");
                        System.out.println("Please enter your rank as an integer.\n\n");
                        in.nextLine();
                    }
                }
            
                Element pick = addPick(newList, ticker, rank);


                //find picks last updated info for possible offline display.
                findLastPickInfo(newList, pick);
                
                //appends pick to list and list to root
                list.appendChild(pick);
                root.appendChild(list);
                
                //TODO: turn this into a function
                System.out.println("Would you like to add another stock to your new list? (Y/N)   ");
                String response = in.next();


                while(!response.equals("N")&!response.equals("Y")){
                    System.out.print("!! INVALID INPUT :: Please Enter Y or N\n\n"
                            + "Would you like to add anouther stock to your new list? (Y/N):  ");
                    in.nextLine();
                    response = in.next();
                }
                if(response.equals("N")|response.equals("n")){
                    System.out.println("!!Please Enter 0 to quit the program and restart so you can view your changes:  ");
                    edit = false;
                }
            }
//   Update DOM
            DOMSource update = new DOMSource(doc);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult previous = new StreamResult("src/com/test/itemsEX.xml");
            transformer.transform(update, previous);
        }
        catch(ParserConfigurationException | SAXException | IOException | TransformerException e){
            System.err.println(e);
        }
    }

    private void findLastPickInfo(StockList list, Element pick){
        //create price element for Price
        Element p = doc.createElement("lastUpdatedPrice");
        //instantiate price string
        String lastUpdatedPrice;
        
        //find place
        int place = list.picks.size()-1;
        //get latest price
        lastUpdatedPrice = list.picks.get(place).getSharePrice().toString();
        
        //append elements to DOM
        p.appendChild(doc.createTextNode(lastUpdatedPrice));
        pick.appendChild(p);
    }
    /**
     * adds pick to specified list
     * creates Pick object
     * creates XML element to append
     * @param list
     * @param ticker
     * @return Element
     */
    private Element addPick(StockList list, String ticker, int rank){
        //Instantiate necessary variables
        Element pick = doc.createElement("pick");
        Element tik = doc.createElement("ticker");
        Element r = doc.createElement("rank");

        //Instantiate Pick Object in StockList
        list.addPick(ticker, rank);


        //create xml element and return
        tik.appendChild(doc.createTextNode(ticker));
        pick.appendChild(tik);
        r.appendChild(doc.createTextNode(Integer.toString(rank)));
        pick.appendChild(r);
        return pick;
    }
    
//    public void removeList(String listName){
//        //get node list
//        NodeList listNodes = doc.getElementsByTagName("list");
//        //create parser
//        StockListParser parser = new StockListParser();
//        //test sizes
//        System.out.println(listNodes.getLength());
//        System.out.println(parser.getStockLists().size());
//        //find proper node
//        for (int i=0;i<listNodes.getLength();i++){
//            if (listName.equals(parser.getStockList(i).getListCategory())){
//                //found
//                Element listToRemove = (Element)doc.getElementsByTagName("list").item(i);
//                //one more test
//                System.out.println(listToRemove.getAttributeNode("cat")+" must match "+ listName);
//                //remove
//                doc.removeChild(listNodes.item(i));
//            }
//        }
//    }
//    public void addToList(String listName){
//        
//    //Instantiate necessary variables
//                //define root
//                Element root = doc.getDocumentElement();
//                //creates node list
//                NodeList listNodes = doc.getElementsByTagName("cat");
//                //create list Element
//                Element currentList;
//                //find proper node
//                for(int i = 0; i<listNodes.getLength(); i++){
//                    if (listName == (Element)listNodes.item(i)){
//                        currentList = (Element)listNodes.item(i);
//                    }
//                    else {
//                        System.out.println("No Match Try Again");
//                    }
//                }
//                
//
//                
//            System.out.print("Please enter the ticker of the stock you would like to add:   ");
//            String ticker = in.next();
//            int rank = 0;
//                //Error Handler
//            boolean valid = false;
//            while (!valid) {
//                try {
//
//                    System.out.print("Please enter a rank you would like to associate with the stock:   ");
//                    rank = in.nextInt();
//
//                    valid = true;
//                }
//                catch(InputMismatchException e) {
//                    System.err.println("\nINVALID INPUT::   Please enter your rank as an integer:   ");
//                    rank = in.nextInt();
//                }
//                
//            }
//                
//            //create element
//            Element newElement=addPick(list, ticker, rank);
//            root.appendChild(newElement);
//                
//            //   Update DOM
//            try{
//            DOMSource update = new DOMSource(doc);
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            StreamResult previous = new StreamResult("src/com/test/itemsEX.xml");
//            transformer.transform(update, previous);
//            }
//            catch(TransformerException e){
//                e.printStackTrace();
//            }
}
