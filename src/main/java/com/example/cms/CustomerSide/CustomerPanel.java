package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Inventory;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerPanel extends Application {


Inventory inv;
Customer customer;
    public CustomerPanel(Customer customer) throws IOException, ClassNotFoundException {
        inv = new Inventory();
        this.customer = customer ;
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

        ListView<VBox> listView = new ListView<>();
        ComboBox<String> categories = new ComboBox<>();
        Main.getCategories(categories);
        categories.getItems().add("All");
        categories.getSelectionModel().select("All");

        ComboBox<String> subCategories = new ComboBox<>();
        subCategories.setOnAction(e->{
            Main.getItemsCustomer(inv , listView ,customer ,categories, subCategories ,stage);
        });
        Main.getSubCategories(subCategories);
        subCategories.getItems().addAll("All");
        subCategories.getSelectionModel().select("All");

        HBox dropDown = new HBox();
        Label label = new Label("Choose a category");
        Label labelSub = new Label("Choose a sub category");
        dropDown.setPadding(new Insets(10));
        dropDown.getChildren().addAll(label,categories , labelSub , subCategories);
        HBox.setMargin(categories , new Insets(10));




        borderPane.setBottom(dropDown);
        categories.setOnAction(e->{
            Main.getItemsCustomer(inv , listView ,customer ,categories, subCategories ,stage);

        });
        Main.getItemsCustomer(inv, listView , customer, categories ,subCategories, stage);

        borderPane.setCenter(listView);



//        Button orders = new Button("Orders");
        Button cart = new Button("Cart"); // maybe image in the future
cart.setOnAction(e->{
    Cart cart1 = new Cart(customer);
    cart1.start(stage);
});
        Button profile = new Button("Profile");

        //Label l1 = new Label("Welcome " + customer.getFirstName());

        Label l1 = new Label( String.valueOf(customer.getCart().getCount()));
        Button signout=new Button("signout");
        signout.setOnAction(
                e->{
                    Main x=new Main();
                    try {
                        x.start(stage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        hBox.getChildren().addAll(profile,cart , l1,signout);
        HBox.setMargin(cart, new Insets(0,15,0,0));



        borderPane.setTop(hBox);
        HBox.setMargin(profile, new Insets(0,15,0,0));




        stage.setScene(new Scene(borderPane, 500, 500));
        stage.show();
    }
}
