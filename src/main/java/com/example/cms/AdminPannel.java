package com.example.cms;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AdminPannel extends Application {


    public static void main(String[] args) {
       launch();


    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane = Main.GetDefaultPane();
        BorderPane border = new BorderPane();


        HBox box = new HBox(10);

        box.setPadding( new Insets(10));
        box.setMaxHeight(200);
        box.setMaxWidth(10000);

        Button orders = new Button("Order");
        Button customers = new Button("Customers");
        box.getChildren().addAll(orders , customers);
        HBox.setMargin(orders , new Insets(10));
        HBox.setMargin(customers , new Insets(10));
        //ScrollPane items = new ScrollPane();

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Item 1" , "Item 2" , "Item 3" , "Item 4 ");
        border.setCenter(listView);
//        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<String>() { what i wanted was when I click the thing more info appears
//
//        });

        Text text = new Text("Welcome");
        border.setTop(box);




        box.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        Button ViewAllItems = new Button();

        stage.setScene(new Scene(border , 500 , 500) );
        stage.show();
    }
}
