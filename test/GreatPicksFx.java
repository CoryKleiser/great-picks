///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.test;
//
//import com.start.Pick;
//import com.start.StockList;
//import com.start.StockListParser;
//import java.util.ArrayList;
//import java.util.Set;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
///**
// *
// * @author moku
// */
//public class GreatPicksFx extends Application {
//    //instantiate variables
//    ArrayList<StockList> lists;
//    StockList selectedList;
//    ArrayList<Pick> listPicks;
//    Button changerButton;
//    ArrayList<Button> categoryButtons = new ArrayList<>();
//    ArrayList<Button> pickButtons = new ArrayList<>();
//    FlowPane fpPortfolio, fpList;
//    Stage stage;
//    Label lblPostfolio, lblList;
//    Scene scenePortfolio, sceneList;
//    StockListParser parser;
//    
//    /**
//     * function to generate buttons listing user's portfolio names
//     * @param info
//     * @param btnList 
//     */
//    private void getCategoryButtons(ArrayList<StockList> info, ArrayList<Button> btnList){
//        lists = info;
//        
//        int j = info.size();
//        for(int i=0; i<j; i++){
//            btnList.add(new Button(info.get(i).getListCategory()));
//        }
//        
//
//    }
//    
//    /**
//     * function to generate list Buttons for users stock picks
//     * @param list
//     * @param btnList 
//     */
//    private void getListButtons(StockList list, ArrayList<Button> btnList){
//        listPicks = list.getPicks();
//        int j = listPicks.size();
//        for(int i=0; i<j; i++){
//            btnList.add(new Button(listPicks.get(i).getTicker()));
//        }
//    }
//    
//    /**
//     * button event handler
//     * @param e 
//     */
//    private void handleButtonAction(ActionEvent e){
//        int cycle = categoryButtons.size();
//        for(int i=0; i<cycle; i++){
//            if (e.getTarget()==categoryButtons.get(i)){
//                selectedList = parser.getStockList(i);
//                stage.setScene(sceneList);
//            }
//        }
//        if(e.getTarget()==changerButton){
//            System.out.println("TODO:: Set up stock page");
//        }
//    }
//    
//    @Override
//    public void start(Stage primaryStage) {
//
//        
//        
//        
//        //create Parser and Parse existing content
//        parser = new StockListParser();
//        parser.parse("src/com/test/itemsEX.xml");
//
//        //declare stage
//        stage = primaryStage;
//
//
//
////Set up scene 1
//        //create buttons
//        changerButton = new Button("Edit Portfolio");
//        changerButton.setOnAction(e -> handleButtonAction(e));
//        //create buttons from category info
//        getCategoryButtons(parser.getStockLists(), categoryButtons);
//        for (Button catButton : categoryButtons){
//            catButton.setOnAction(e -> handleButtonAction(e));
//        }
//        
//
//
//        //create flow pane
//        fpPortfolio = new FlowPane();
//        fpPortfolio.getChildren().add(changerButton);
//        for(Button catButton : categoryButtons){
//            fpPortfolio.getChildren().add(catButton);
//        }
//        fpPortfolio.setPadding(new Insets(15));
//        fpPortfolio.setStyle("-fx-background: [204, 255, 255];");
//
//        
////Set up scene 2
//        //TODO: create buttons from List info
//        for (Button catButton : categoryButtons){
//            catButton.setOnAction(e -> handleButtonAction(e));
//        }
//
//        //create flow pane
//        fpList = new FlowPane();
//        fpList.getChildren().add(changerButton);
//        for(Button catButton : categoryButtons){
//            fpList.getChildren().add(catButton);
//        }
//        fpList.setPadding(new Insets(15));
//     //   fpList.setStyle("-fx-background: tan;");
//
//        scenePortfolio = new Scene(fpPortfolio, 500, 750);
//        sceneList = new Scene(fpList, 500, 750);
//        System.out.println("TTTTTTTTEEEEEEEESSSSSSSTTTTTTTTT\n" + parser.getStockLists().get(0).getPicks().get(0).tik);
//        
//        //declare root
//        StackPane root = new StackPane();
//
//
//        stage.setTitle("Great Picks");
//        stage.setScene(scenePortfolio);
//        stage.focusedProperty();
//        stage.show();
//    }
//    
//
//    
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        Application.launch(args);
//    }
//}
