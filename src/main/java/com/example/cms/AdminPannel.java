package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
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
import java.util.HashMap;
import java.util.Map;

public class AdminPannel extends Application {
    //private Map<Integer , Product> products = new HashMap<>();

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
        customers.setOnAction(e->{
            CustomersView customersView = new CustomersView();
            customersView.start(stage);
        });
        box.getChildren().addAll(orders , customers);
        HBox.setMargin(orders , new Insets(10));
        HBox.setMargin(customers , new Insets(10));
        //ScrollPane items = new ScrollPane();

        ListView<String> listView = new ListView<>();
//        listView.getItems().addAll("Item 1" , "Item 2" , "Item 3" , "Item 4 ");
//        border.setCenter(listView);
//        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<String>() { what i wanted was when I click the thing more info appears
//
//        });
//        products = inv.getProducts();
//        for(Product p : products.values())
//        {
//            listView.getItems().add(p.toString());
//        }


        Text text = new Text("Welcome");
        border.setTop(box);

        // button to add items opens a new window and returns to the previous window after ading the item





        box.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        Button ViewAllItems = new Button();

        stage.setScene(new Scene(border , 500 , 500) );
        stage.show();
    }
}
