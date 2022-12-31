package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import com.example.cms.Classes.Validations;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        ComboBox<String> category = new ComboBox<>();
        Main.getCategories(category);
        category.getSelectionModel().select(category.getItems().indexOf(product.getCategory()));
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

        Label l9 = new Label("ImageSource");
        TextField  ImageSource = new TextField(product.ImageSource);
        gridPane.add(l9,0 , 12);
        gridPane.add(ImageSource , 1 , 12);


        Button edit = new Button("Edit");
        edit.setOnAction(e->{

            if(Validations.isInt(ID) && Validations.isDouble(basePrice) && Validations.isInt(quantity))
            {
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
                }
                inv.getProducts().remove(id);
                if(Validations.idExists( inv, Integer.parseInt(ID.getText()))) // so that we don't add same id
                {
                    ID.setText("ID already exists");
                    ID.setStyle("-fx-text-fill: red");

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("ID already exists");
                    return;
                }

                else if (!Validations.isDouble((basePrice)))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please enter a valid price");
                    alert.showAndWait();
                    return;
                }
                else if(!Validations.isInt(quantity))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please enter a valid quantity");
                    alert.showAndWait();
                    return;
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
                    stage.close();
                }
                ItemDetails.SaveBackToAdmin(stage, inv);

            }
        });

        EditItem.ItemButtons(stage, gridPane,edit );
    }

//    public  boolean Confirm(TextField ID, TextField name, TextField color, ComboBox category, TextField size, TextArea description, TextField basePrice, TextField quantity, TextField imageSource) {
//
//        try {
//            inv.getProducts().remove(id);
//            inv.addProduct(new Product(Integer.parseInt(ID.getText()), name.getText() , color.getText() , (String) category.getSelectionModel().getSelectedItem(), size.getText() , description.getText() , Double.parseDouble(basePrice.getText()) , Integer.parseInt(quantity.getText()), imageSource.getText()));
//        }
//        catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//        return false;
//    }


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

        gridPane.add(edit , 2 , 14);
        gridPane.add(back , 1 , 14);
        stage.setScene(new Scene(gridPane , 650 , 600));
        stage.show();
    }
}
