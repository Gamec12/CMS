package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Inventory;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerPanel extends Application {


    Inventory inv;
    Customer customer;

    public CustomerPanel(Customer customer) throws IOException, ClassNotFoundException {
        inv = new Inventory();
        this.customer = customer;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {



        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);


        VBox vBox = new VBox();
        setHboxColor(hBox);


        ListView<VBox> listView = new ListView<>();
        ComboBox<String> categories = new ComboBox<>();
        Main.getCategories(categories);
        categories.getItems().add("All");
        categories.getSelectionModel().select("All");

        ComboBox<String> subCategories = new ComboBox<>();
        subCategories.setOnAction(e -> {
            Main.getItemsCustomer(inv, listView, customer, categories, subCategories, stage);
        });
        Main.getSubCategories(subCategories);
        subCategories.getItems().addAll("All");
        subCategories.getSelectionModel().select("All");

        HBox dropDown = new HBox();
        Label label = new Label("Choose a category");
        Label labelSub = new Label("Choose a sub category");
        dropDown.setPadding(new Insets(10));
        dropDown.getChildren().addAll(label, categories, labelSub, subCategories);
        setHboxColor(dropDown);
        HBox.setMargin(categories, new Insets(10));


        borderPane.setBottom(dropDown);
        categories.setOnAction(e -> {
            Main.getItemsCustomer(inv, listView, customer, categories, subCategories, stage);

        });
        Main.getItemsCustomer(inv, listView, customer, categories, subCategories, stage);

        borderPane.setCenter(listView);


//        Button orders = new Button("Orders");
        Button cart = Main.getDefaultButton("Cart"); // maybe image in the future
        cart.setOnAction(e -> {
            Cart cart1 = new Cart(customer);
            cart1.start(stage);
        });

        //Label l1 = new Label("Welcome " + customer.getFirstName());

        cart.setText("Cart: " + customer.getCart().getCount());
        Button signOut = Main.getLogOutButton("← sign out");
        signOut.setOnAction(
                e -> {
                    Main x = new Main();
                    try {
                        x.start(stage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });


        HBox.setMargin(cart, new Insets(0, 15, 0, 0));


        borderPane.setTop(hBox);
        hBox.getChildren().addAll(signOut, cart );


        stage.setScene(new Scene(borderPane, 500, 500));
        stage.show();
    }

    private static void setHboxColor(HBox dropDown) {
        dropDown.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY , null , null)));
    }
}
