package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class EditItem extends Application {
    Inventory inv;
    int id;

    public EditItem(Inventory inv, int id) {
        this.inv = inv;
        this.id = id;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        GridPane gridPane = Main.GetDefaultPane();
        Product product = inv.getProduct(id);
        inv = new Inventory();
        Text title = new Text("Edit the item's details");
        title.setFont(Font.font("arial", FontWeight.BOLD, 18));
        gridPane.add(title , 0 , 0 , 2 ,2);

        Label l1 = new Label("Item ID");
        TextField ID = new TextField();
        ID.setText(String.valueOf(product.getItemID()));
        gridPane.add(l1,0 , 3);
        gridPane.add(ID , 1 , 3);



        Label l2 = new Label("Name");
        TextField name = new TextField(product.getName());
        gridPane.add(l2,0 , 4);
        gridPane.add(name , 1 , 4);
        // String color;

        Label l3 = new Label("Color");
//        ColorPicker colorPicker = new ColorPicker();
//        colorPicker.setOnAction(e->
//        {
//
//        });

        TextField  color = new TextField(product.getColor());
        gridPane.add(l3,0 , 5);
        gridPane.add(color , 1 , 5);

        Label l4 = new Label("Category");
        TextField  category = new TextField(product.getCategory());
        gridPane.add(l4,0 , 6);
        gridPane.add(category , 1 , 6);

        Label l5 = new Label("Size");
        TextField  size = new TextField(product.getSize());
        gridPane.add(l5,0 , 7);
        gridPane.add(size , 1 , 7);

        Label l6 = new Label("description");
        TextArea description = new TextArea(product.getDescription());
        gridPane.add(l6,0 , 8);
        gridPane.add(description , 1 , 9);

        Label l7 = new Label("Price");
        TextField  basePrice = new TextField( String.valueOf(product.getBasePrice()) );
        gridPane.add(l7,0 , 10);
        gridPane.add(basePrice , 1 , 10);

        Label l8 = new Label("Quantity");
        TextField  quantity = new TextField(String.valueOf(product.getQuantity()) );
        gridPane.add(l8,0 , 11);
        gridPane.add(quantity , 1 , 11);
        Button edit = new Button("Edit");
        edit.setOnAction(e->{


            inv.getProducts().remove(id);

            try {
                inv.addProduct(new Product(Integer.parseInt(ID.getText()),name.getText() ,color.getText() , category.getText() , size.getText() , description.getText() , Double.parseDouble(basePrice.getText()) , Integer.parseInt(quantity.getText())));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                inv.save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        ItemButtons(stage, gridPane, edit);
    }

    static void ItemButtons(Stage stage, GridPane gridPane, Button edit) {
        Button back = new Button("Back");
        back.setOnAction(e->{
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        gridPane.add(edit , 2 , 12);
        gridPane.add(back , 1 , 12);
        stage.setScene(new Scene(gridPane , 650 , 600));
        stage.show();
    }
}
