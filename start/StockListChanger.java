/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.io.File;
import java.io.IOException;
import static java.lang.System.in;
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
import org.xml.sax.SAXException;


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
                System.out.print("Please enter the ticker of the stock you would like to add:   ");
                String ticker = in.next();
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
                        System.err.println("\nINVALID INPUT::   Please enter your rank as an integer:   ");
                        rank = in.nextInt();
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
                    System.out.println("!! INVALID INPUT :: Please Enter Y or N");
                    response = in.next();
                }            
                if(response.equals("N")){
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
        Element p = doc.createElement("lastUpdatedPrice");
        String lastUpdatedPrice = "";

        int place = list.picks.size()-1;
        boolean t=false;

        while(!t){
            try{

                lastUpdatedPrice = list.picks.get(place).getSharePrice().toString();
                t=true;
            }
            catch(NullPointerException e){
                System.out.println("That is not a Ticker on any market I know. Please try again.   ");
            }
        }
       
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
}
