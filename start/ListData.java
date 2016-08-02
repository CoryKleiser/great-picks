//package com.start;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.util.ArrayList;
//import java.util.List;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
////TODO: reconfigure for a list of stock lists rather than just single stocks
//
///**
// *This class is a conduit the random access file containing the stock lists
// * @author moku
// */
//public class ListData {
//    private RandomAccessFile listData;
//    
//    public static final int TICKER_SIZE = 4;
//    public static final int PRICE_SIZE = 8;
//    public static final int RANK_SIZE = 2;
//    public static final int RECORD_SIZE = TICKER_SIZE + RANK_SIZE + PRICE_SIZE;
//    
//    /**
//     * constructs ListData object
//     */
//    public ListData(){
//        listData = null;
//    }
//    
//    /**
//     * Opens data file
//     * @param filename name of listData datafile to open
//     */
//    public void open(String filename){
//        try{
//            if (listData != null) { listData.close(); }
//            listData = new RandomAccessFile(filename, "rw");
//        }
//        catch(IOException e){
//            System.err.println("Caught IOException: " + e.getMessage());
//        }
//    }
//    
//    /**
//     * Closes data file
//     */
//    public void close(){
//        try{
//            if (listData != null) { listData.close(); }
//            listData = null;
//        }
//        catch(IOException e){
//            System.err.println("Caught IOException: " + e.getMessage());
//        }
//    }
//    
//    /**
//     * Gets the number of Picks in List
//     * @return 
//     */
//    public int size(){
//        try { return (int) (listData.length() / RECORD_SIZE); }
//        catch(IOException e){ System.err.println("Caught IOException: " + e.getMessage()); 
//        //NEED TO FIND OUT HOW TO FIX return expected ERROR
//        return 0; 
//        }
//    }
//    /**
//     * Reads and returns pick data
//     * @param n
//     * @return 
//     */
//    public Pick read(int n){
//        try {
//        listData.seek(n * RECORD_SIZE);
//        String ticker = listData.readLine();
//        int rank = listData.readInt();
//        float sharePrice = listData.readFloat();
//        //TODO: return last recorded data so there is some offline functionality
//        //float sharePrice = listData.readFloat();
//        return new Pick(ticker, rank);
//        
//        }
//        catch(IOException e){
//            System.err.println("Caught IOException: " + e.getMessage());
//            return null;
//        }
//    }
//    /**
//     * turns ticker into array of chars
//     * @param ticker
//     * @return array of ticker chars
//     */
//    private char[] charify(String ticker){
//        int charCount = ticker.length();
//        char[] t = new char[charCount];
//        for(int i=0; i < charCount; i++){
//            char current = ticker.charAt(i);
//            System.out.print(current);
//            t[i] = current;
//        }
//        System.out.println("charify test");
//        System.out.print(t);
//        return t;
//    }
//    /**
//     * finds pick location in listData
//     * @param ticker
//     * @return 
//     */
//    public int find(String ticker){
//        char[] tickerChars = charify(ticker);
//        System.out.println("find test 1");
//        System.out.print(tickerChars);
//        String t = null;
//        try {
//          FileInputStream fis = new FileInputStream("data.dat");
//          char current;
//
//          for (int i=0; i<size(); i++) {
//            current = listData.readChar();
//            System.out.println("find for test" + i);
//            System.out.println(current);
//            if (current == tickerChars[0]){
//                t = String.valueOf(current);
//                System.out.println("test for t in find");
//                System.out.print(t);
//                for (int j = 0; j < ticker.length(); j++){
//                    current = (char) fis.read();
//                    String s = String.valueOf(current);
//                    t = t + s;
//                    System.out.println("ticker test" + j);
//                    System.out.print(t);
//                }
//            }
//            if (ticker.equals(t)){
//                return i;
//            }
//            else {
//                return -1;
//            }
//          }
//        }
//        catch (IOException e) {
//          System.err.println("!!ERROR:: IOException:  " + e.getMessage());
//        }
////        try {
////            for (int j = 0; j < size(); j++){
////            listData.seek(i * RECORD_SIZE);
////            String t = listData.find();
////            if (t.equals(ticker)) { return i; }
////            // Found a match            
////            }
////        }
////        catch(IOException e){
////            System.err.println("Caught IOException: " + e.getMessage());
////            //NEED TO FIND OUT HOW TO FIX return expected ERROR
////            return 0;
////        }
//          return -1;
//    }
//    
//    public void write(int n, Pick pick){
//        try {
//            listData.seek(n * RECORD_SIZE);
//            listData.writeChars(pick.getTicker());
//            listData.writeFloat(pick.getSharePrice().floatValue());
//            listData.writeInt(pick.getListRank());
//        }
//        catch(IOException e){
//            System.err.println("Caught IOException: " + e.getMessage());
//        }
//    }
//}
