package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItem extends Application {
    Inventory inv = new Inventory();

    public AddItem(Inventory inv) throws IOException, ClassNotFoundException {
        this.inv = inv;
    }

    public static void main(String[] args) {
        launch(args);





    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane =Main.GetDefaultPane();
        Text title = new Text("Enter the item's details");
        title.setFont(Font.font("arial", FontWeight.BOLD, 18));
        gridPane.add(title , 0 , 0 , 2 ,2);

        Label l1 = new Label("Item ID");
        TextField ID = new TextField();
        gridPane.add(l1,0 , 3);
        gridPane.add(ID , 1 , 3);



        Label l2 = new Label("Name");
        TextField name = new TextField();
        gridPane.add(l2,0 , 4);
        gridPane.add(name , 1 , 4);
        // String color;

        Label l3 = new Label("Color");
//        ColorPicker colorPicker = new ColorPicker();
//        colorPicker.setOnAction(e->
//        {
//
//        });

        TextField  color = new TextField();
        gridPane.add(l3,0 , 5);
        gridPane.add(color , 1 , 5);

        Label l4 = new Label("Category");
        TextField  category = new TextField();
        gridPane.add(l4,0 , 6);
        gridPane.add(category , 1 , 6);

        Label l5 = new Label("Size");
        TextField  size = new TextField();
        gridPane.add(l5,0 , 7);
        gridPane.add(size , 1 , 7);

        Label l6 = new Label("description");
        TextArea description = new TextArea();
        gridPane.add(l6,0 , 8);
        gridPane.add(description , 1 , 9);

        Label l7 = new Label("Price");
        TextField  basePrice = new TextField();
        gridPane.add(l7,0 , 10);
        gridPane.add(basePrice , 1 , 10);

        Label l8 = new Label("Quantity");
        TextField  quantity = new TextField();
        gridPane.add(l8,0 , 11);
        gridPane.add(quantity , 1 , 11);
        Button add = new Button();
        add.setOnAction(e->{
            Product product = new Product(Integer.parseInt(ID.getText()),name.getText() ,color.getText() , category.getText() , size.getText() , description.getText() , Double.parseDouble(basePrice.getText()) , Integer.parseInt(basePrice.getText()));
            try {
                inv.addProduct(product);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        gridPane.add(add , 2 , 12);
        stage.setScene(new Scene(gridPane , 500 , 500));
        stage.show();





    }
}