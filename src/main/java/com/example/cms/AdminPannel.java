package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminPannel extends Application {
    private Map<Integer , Product> products = new HashMap<>();

    public static void main(String[] args) {
       launch();


    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Inventory inv = new Inventory();
        GridPane gridPane = Main.GetDefaultPane();
        BorderPane border = new BorderPane();

        HBox box = gethBox();

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
        for(Product p : inv.getProducts().values())
        {
            listView.getItems().add(p.toString());
            System.out.println(p.toString());
        }
        border.setCenter(listView);
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

         HBox bottom = gethBox();
         bottom.setAlignment(Pos.BOTTOM_LEFT);
         Button add = new Button("Add a product");
         add.setOnAction(e->{
             AddItem addItem = null;
             try {
                 addItem = new AddItem(inv);
             } catch (IOException ex) {
                 throw new RuntimeException(ex);
             } catch (ClassNotFoundException ex) {
                 throw new RuntimeException(ex);
             }
             addItem.start(stage);
         });
         bottom.getChildren().addAll(add);
         border.setBottom(bottom);

        // button to add items opens a new window and returns to the previous window after adding the item





        box.setBackground(new Background(new BackgroundFill(Color.BLACK , null , null)));
        Button ViewAllItems = new Button();

        stage.setScene(new Scene(border , 500 , 500) );
        stage.show();
    }

    private static HBox gethBox() {
        HBox box = new HBox(10);

        box.setPadding( new Insets(10));
        box.setMaxHeight(200);
        box.setMaxWidth(10000);
        return box;
    }
}
