package com.example.cms.AdminSide;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemDetails extends Application {
    int id;
    Inventory inv;

    public ItemDetails(int id, Inventory inv) {
        this.id = id;
        this.inv = inv;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane = Main.GetDefaultPane();
        Product product = inv.getProduct(id);

        Text text = new Text("Item details");
        Main.setText(text);
        gridPane.add(text, 0, 0, 2, 2);

        bigLabel l1 = new bigLabel("ID:");
        TextField ID = new TextField(String.valueOf(product.getItemID()));
        ID.setEditable(false);
        gridPane.add(l1, 0, 3);
        gridPane.add(ID, 1, 3);

        bigLabel l2 = new bigLabel("Name:");
        TextField name = new TextField(product.getName());
        name.setEditable(false);
        gridPane.add(l2, 0, 4);
        gridPane.add(name, 1, 4);

        bigLabel l3 = new bigLabel("Color:");
        TextField color = new TextField(product.getColor());
        color.setEditable(false);
        gridPane.add(l3, 0, 5);
        gridPane.add(color, 1, 5);

        bigLabel l4 = new bigLabel("Category:");
        TextField category = new TextField(product.getCategory());
        category.setEditable(false);
        gridPane.add(l4, 0, 6);
        gridPane.add(category, 1, 6);

        bigLabel l5 = new bigLabel("Size:");
        TextField size = new TextField(String.valueOf(product.getSize()));
        size.setEditable(false);
        gridPane.add(l5, 0, 7);
        gridPane.add(size, 1, 7);

        bigLabel l6 = new bigLabel("Description:");
        TextArea description = new TextArea(String.valueOf(product.getDescription()));
        description.setEditable(false);
        gridPane.add(l6, 0, 8);
        gridPane.add(description, 1, 8);

        bigLabel l7 = new bigLabel("Price:");
        TextField price = new TextField("$" + String.valueOf(product.getBasePrice()));
        price.setEditable(false);
        gridPane.add(l7, 0, 10);
        gridPane.add(price, 1, 10);

        Label l8 = new bigLabel("Quantity:");
        TextField quantity = new TextField(String.valueOf(product.getQuantity()));
        quantity.setEditable(false);
        gridPane.add(l8, 0, 11);
        gridPane.add(quantity, 1, 11);

        bigLabel l9 = new bigLabel("Image Source");

        Button back = new Button("Back");
        back.setOnAction(e -> {
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        gridPane.add(back, 0, 12);

        Button edit = new Button("Edit Item");
        edit.setOnAction(e -> {


            EditItem editItem = new EditItem(inv, id);

            try {
                editItem.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });

        Button delete = new Button("Delete");
        delete.setOnAction(e -> {
            inv.getProducts().remove(id);
            SaveBackToAdmin(stage, inv);
        });


        HBox box = new HBox();
        box.getChildren().addAll(edit, delete);
        box.setPadding(new Insets(10));
        HBox.setMargin(delete, new Insets(0, 0, 0, 13));
        gridPane.add(box, 1, 12);

        stage.setScene(new Scene(gridPane, 750, 650));
        stage.show();

    }

    static void SaveBackToAdmin(Stage stage, Inventory inv) {
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
    }

    static class bigLabel extends Label {
        public bigLabel(String name) {
            super(name);
            fontProperty().set(Font.font("arial", FontWeight.BOLD, 20));

        }
    }
}
