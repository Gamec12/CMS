package com.example.cms.AdminSide;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import com.example.cms.Classes.Validations;
import com.example.cms.Main;
import javafx.application.Application;
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
        GridPane gridPane = Main.GetDefaultPane();
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
        ComboBox<String> category = new ComboBox<>();
        Main.getCategories(category);
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
        Label l9 = new Label("Image source");
        TextField ImageSource = new TextField();
        gridPane.add(l9 , 0 , 12);
        gridPane.add(ImageSource , 1 , 12);

        Button add = Main.getDefaultButton("Add");

        add.setOnAction(e->{
            if(name.getText().equals("") || color.getText().equals("") || category.getValue().equals("") || size.getText().equals("") || description.getText().equals("") || basePrice.getText().equals("") || quantity.getText().equals("") || ImageSource.getText().equals("") || category.getSelectionModel().getSelectedItem() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please fill all the fields");
                alert.showAndWait();
                return;
            }

            else if(!Validations.isInt(ID))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please enter a valid ID");
                alert.showAndWait();
                return;
            }

            if(Validations.idExists( inv, Integer.parseInt(ID.getText()))) // so that we don't add same id
            {
                ID.setText("ID already exists");
                ID.setStyle("-fx-text-fill: red");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ID already exists");
                alert.showAndWait();}

             else if (!Validations.isDouble((basePrice)))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please enter a valid price");
                alert.showAndWait();
            }
            else if(!Validations.isInt(quantity))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please enter a valid quantity");
                alert.showAndWait();
            }
            else
            {
                Product p = new Product(Integer.parseInt(ID.getText()) , name.getText() , color.getText() , category.getValue() , size.getText() , description.getText() , Double.parseDouble(basePrice.getText()) , Integer.parseInt(quantity.getText()) , ImageSource.getText());
                try {
                    inv.addProduct(p);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Item added successfully");
                alert.showAndWait();
                AdminPannel ap = new AdminPannel();
                try {
                    ap.start(stage);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        EditItem.ItemButtons(stage, gridPane, add);

    }


}
