/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.start;

import java.io.File;
import java.io.IOException;
import static java.lang.System.in;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    
    public StockListChanger(){

    }
    
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
                Element tik = doc.createElement("ticker");
                tik.appendChild(doc.createTextNode(ticker));
                list.appendChild(tik);
            
            
                System.out.print("Please enter a rank you would like to associate with the stock:   ");
                int rank = in.nextInt();
                Element r = doc.createElement("rank");
                r.appendChild(doc.createTextNode(Integer.toString(rank)));
                list.appendChild(r);
            
                newList.addPick(ticker, rank);
            
                Element p = doc.createElement("lastUpdatedPrice");

//TODO:: Create and add price: 
                //Maybe add function then call to update all prices on list.
                //Maybe add i int at beginning of while to acess array then convert and add to xml.
            
//            p.appendChild(doc.createTextNode());
            
                System.out.println("Would you like to add another stock to your new list? (Y/N)   ");
                String response = in.next();
            
                root.appendChild(list);

                while(!response.equals("N")&!response.equals("Y")){
                    System.out.println("     !! INVALID INPUT :: Please Enter Y or N");
                    response = in.next();
                    System.out.println(response);
                }            

                if(response.equals("Y")){
                    continue;
                }

                else{
                    System.out.println(response);
                    edit = false;
                }
            }
        }
        catch(ParserConfigurationException | SAXException | IOException e){
            System.err.println(e);
        }
    }
}
