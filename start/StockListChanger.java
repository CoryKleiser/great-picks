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
import javax.xml.transform.TransformerConfigurationException;
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
 * @author moku
 */
public class StockListChanger {
    private DocumentBuilder builder;
    private Document doc;
    private XPath path;


    
    public void addNewList(){
            
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            builder = documentBuilderFactory.newDocumentBuilder();
            doc = builder.parse("src/com/test/itemsEX.xml");
            Element root = doc.getDocumentElement();

        
            Scanner in = new Scanner(System.in);
        
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
                Element pick = doc.createElement("pick");
                Element tik = doc.createElement("ticker");
                tik.appendChild(doc.createTextNode(ticker));
                pick.appendChild(tik);
            
                boolean valid = false;
                while (!valid) {
                    try {
                        System.out.print("Please enter a rank you would like to associate with the stock:   ");
                        rank = in.nextInt();
                        

                        valid = true;
                    }
                    catch(InputMismatchException e){
                        System.err.println("\nPlease enter your rank as an integer:   ");
                        rank = in.nextInt();
                    }
                }
                Element r = doc.createElement("rank");                
                r.appendChild(doc.createTextNode(Integer.toString(rank)));
                pick.appendChild(r);
                newList.addPick(ticker, rank);

                
                

            

//TODO:: Add all variables taken from the yahooAPI to one function

                findLastPickInfo(newList, pick);
            
                System.out.println("Would you like to add another stock to your new list? (Y/N)   ");
                String response = in.next();
                list.appendChild(pick);
                root.appendChild(list);

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

    private void findLastPickInfo(StockList newList, Element pick){
        Element p = doc.createElement("lastUpdatedPrice");

        int place = newList.picks.size()-1;
        String lastUpdatedPrice = newList.picks.get(place).sharePrice.toString();
        p.appendChild(doc.createTextNode(lastUpdatedPrice));
        pick.appendChild(p);
    }
}
