package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemDetails extends Application {
    String itemSelected;
    Inventory inv;

    public ItemDetails(String itemSelected, Inventory inv) {
        this.itemSelected = itemSelected;
        this.inv = inv;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane = Main.GetDefaultPane();
        int id;
        id = Integer.parseInt(itemSelected.substring(itemSelected.indexOf('=') +1 , itemSelected.indexOf(','))); // to get id
        System.out.println(id);
        Product product = inv.getProduct(id);

        Text text = new Text("Item details");
        Main.setText(text);
        gridPane.add(text , 0 , 0 ,2, 2);

        Label l1 = new Label("ID :");
        Label ID = new Label( String.valueOf(product.getItemID()));
        gridPane.add(l1 , 0 , 3);
        gridPane.add(ID , 1 , 3);

        Label l2 = new Label("Name :");
        Label Name = new Label( String.valueOf(product.getName()));
        gridPane.add(l2 , 0 , 4);
        gridPane.add(Name , 1 , 4);

        Label l3 = new Label("Color :");
        Label color = new Label( String.valueOf(product.getColor()));
        gridPane.add(l3 , 0 , 5);
        gridPane.add(color , 1 , 5);

        Label l4 = new Label("category :");
        Label category = new Label( String.valueOf(product.getCategory()));
        gridPane.add(l4 , 0 , 6);
        gridPane.add(category , 1 , 6);

        Label l5 = new Label("size :");
        Label size = new Label( String.valueOf(product.getSize()));
        gridPane.add(l5 , 0 , 7);
        gridPane.add(size , 1 , 7);

        Label l6 = new Label("description :");
        Label description = new Label( String.valueOf(product.getDescription()));
        gridPane.add(l6 , 0 , 8);
        gridPane.add(description , 1 , 8);

        Label l7 = new Label("description :");
        Label basePrice = new Label( String.valueOf(product.getBasePrice()));
        gridPane.add(l7 , 0 , 9);
        gridPane.add(basePrice , 1 , 9);

        Label l8 = new Label("Quantity");
        Label  quantity = new Label( String.valueOf(product.getQuantity()));
        gridPane.add(l8 , 0 , 10);
        gridPane.add(quantity , 1 , 10);

        Button back = new Button("Back");
        back.setOnAction(e->{
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(back , 0 , 11);

        Button edit = new Button("Edit Item");
        edit.setOnAction(e->{




                EditItem editItem = new  EditItem(inv , id);

                try {
                    editItem.start(stage);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


        });

        Button delete = new Button("Delete");
        delete.setOnAction(e->{
            inv.getProducts().remove(id);
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });


        HBox box = new HBox();
        box.getChildren().addAll(edit , delete);
        box.setPadding(new Insets(10));
        HBox.setMargin(delete , new Insets(10));
        gridPane.add(box , 1 , 11);

        stage.setScene(new Scene(gridPane , 400 , 500));
        stage.show();

    }
}
