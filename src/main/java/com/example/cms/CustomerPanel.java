package com.example.cms;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Inventory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerPanel extends Application {


Inventory inv;
Customer customer;
    public CustomerPanel() throws IOException, ClassNotFoundException {
        inv = new Inventory();
        //this.customer = customer;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane = Main.GetDefaultPane();

        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        VBox vBox = new VBox();
        HBox filter = new HBox();

        ListView<VBox> listView = new ListView<>();
        Main.getItemsCustomer(inv, listView);
        borderPane.setCenter(listView);



//        Button orders = new Button("Orders");
        Button cart = new Button("Cart"); // maybe image in the future
        Button profile = new Button("Profile");
        hBox.getChildren().addAll(profile,cart);
        borderPane.setTop(hBox);
        HBox.setMargin(profile, new Insets(0,15,0,0));


        stage.setScene(new Scene(borderPane, 500, 500));
        stage.show();
    }
}
