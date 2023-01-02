package com.example.cms.AdminSide;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminPannel extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Inventory inv = new Inventory();
        BorderPane border = new BorderPane();

        HBox box = gethBox();

        Button orders = new Button("Orders");
        orders.setOnAction(e->{
            ListOfOrders listOfOrders = new ListOfOrders();
            try {
                listOfOrders.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button customers = new Button("Customers");
        customers.setOnAction(e -> {
            CustomersView customersView = new CustomersView();
            try {
                customersView.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        box.getChildren().addAll(orders, customers);
        HBox.setMargin(orders, new Insets(10));
        HBox.setMargin(customers, new Insets(10));
        //ScrollPane items = new ScrollPane();

        ListView<VBox> listView = new ListView<>();
        Main.getItems(inv, listView);
        border.setCenter(listView);
        listView.setOnMouseClicked(e -> {
            VBox selectedItem = listView.getSelectionModel().getSelectedItem();
            // get the number between the []
            String ID = selectedItem.getChildren().get(1).toString();
            ID = ID.substring(ID.indexOf("\"[") + 2, ID.indexOf("]"));
            System.out.println(ID);

            ItemDetails itemDetails = new ItemDetails(Integer.parseInt(ID), inv);
            if (selectedItem.equals("")) {
                return;
            }
            itemDetails.start(stage);
        });

        border.setTop(box);

        HBox bottom = gethBox();
        bottom.setAlignment(Pos.BOTTOM_LEFT);
        Button add = new Button("Add a product");
        add.setOnAction(e -> {
            AddItem addItem;
            try {
                addItem = new AddItem(inv);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            addItem.start(stage);
        });

        Button delete = new Button("Delete Item");
        delete.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter an item to delete");
            dialog.setHeaderText("Enter the id of an item");
            dialog.setContentText("Item ID");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                inv.getProducts().remove(Integer.parseInt(result.get()));
                try {
                    inv.save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button edit = new Button("Edit Item");
        edit.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Enter an item to delete");
            dialog.setHeaderText("Enter the id of an item");
            dialog.setContentText("Item ID");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                int id = Integer.parseInt(result.get());
                EditItem editItem = new EditItem(inv, id);

                try {
                    editItem.start(stage);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
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
        bottom.getChildren().addAll(add, delete, edit,signout);
        border.setBottom(bottom);

        box.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        stage.setScene(new Scene(border, 500, 500));
        stage.show();
    }

    private static HBox gethBox() {
        HBox box = new HBox(10);

        box.setPadding(new Insets(10));
        box.setMaxHeight(200);
        box.setMaxWidth(10000);
        return box;
    }
}
