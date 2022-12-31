package com.example.cms;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = GetDefaultPane();


        Text title = new Text("Welcome!");
        setText(title);

        Button Register = new Button("Registration");
        Button Login = new Button("Login");
       // Register.setBackground(new Background(new BackgroundFill(Color.TEAL ,null , null)));

        Text admin = new Text("An admin?");
        admin.setOnMouseClicked(e-> {
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.start(stage);
        } );// open admin login

        grid.add(title , 0 , 0 ,2, 1);

        grid.add(Register , 0 , 3);
        grid.add(Login , 1 , 3);
        grid.add(admin , 0 , 4);
        Scene s  = new Scene(grid , 500 , 500);
        stage.setScene(s);
        stage.setTitle("CMS");
        //stage.getIcons().add(new Image("D:\\Uni\\Semister 3\\OOP\\Projects\\CMS\\src\\main\\java\\com\\example\\cms\\Images\\Icon.jpg"));
        stage.show();


    }

    public static void setText(Text title) {
        title.setFont(Font.font("arial", FontWeight.BOLD, 30));
    }

    public static GridPane GetDefaultPane() {
        GridPane grid = new GridPane();
        grid.setPadding( new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setBackground(new Background(new BackgroundFill(Color.TEAL ,null , null)));
        //grid.setStyle("-fx-background-color: #fffff;");

        return grid;
    }


    public static void getItems(Inventory inv, ListView<VBox> listView) {
        for(Product p : inv.getProducts().values())
        {
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(10));
            ImageView imageView;
            try{
                imageView = new ImageView(new Image(p.ImageSource));

                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
            }
            catch (IllegalArgumentException ex)
            {
                imageView = new ImageView(new Image("https://github.com/Gamec12/CMS/blob/b6a4bf0f77234d7e5a406d1e7b6a4a62ed3788fb/src/main/java/com/example/cms/Images/Icon.jpg"));
            }
            // temporrary until I know how to put items


            Text item = new Text(p.toString());


            vBox.getChildren().addAll(imageView,item);
           // vBox.setBackground(new Background(new BackgroundFill(Color.WHITE ,null , null)));

            listView.getItems().add(vBox);

            System.out.println(p.toString());

        }
    }

    public static void getItemsCustomer(Inventory inv, ListView<VBox> listView, Customer customer , ComboBox comboBox) {
        listView.getItems().clear();
        if(comboBox.getSelectionModel().getSelectedItem() == null) {

            for (Product p : inv.getProducts().values()) {
                setItems(listView, customer, p);

                System.out.println(p.toString());
            }
        }
            else
            {

                for(Product p : inv.getProducts().values())
                {
                    if(!Objects.equals(p.getCategory(), (String) comboBox.getSelectionModel().getSelectedItem()))
                    {
                        continue;
                    }
                    setItems(listView, customer, p);

                }


        }
    }

    private static void setItems(ListView<VBox> listView, Customer customer, Product p) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        Button button = new Button("Add to cart");
        button.setOnAction(e->{
            customer.getCart().addProduct(p);

        });
        ImageView imageView;
        try{
            imageView = new ImageView(new Image(p.ImageSource));

            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
        }
        catch (IllegalArgumentException ex)
        {
            imageView = new ImageView(new Image("https://github.com/Gamec12/CMS/blob/b6a4bf0f77234d7e5a406d1e7b6a4a62ed3788fb/src/main/java/com/example/cms/Images/Icon.jpg"));
        }
        HBox hBox = new HBox();
        hBox.setSpacing(30);

        hBox.getChildren().addAll(imageView,button);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        Text item = new Text(p.toString());
        vBox.getChildren().addAll(hBox,item);

        listView.getItems().add(vBox);
    }

    public static void getCategories(ComboBox<String> category) {
        category.getItems().addAll("Hoodie","T-Shirt","Shirt","Pants","Shorts","Sweater","Jacket","Socks","Shoes","Hat","Gloves","Scarf","Underwear","Swimwear","Belt","Jewelry","Watch","Bag","Wallet","Other");
    }

    public static void ConfirmAdd(Stage stage, GridPane gridPane, TextField ID, TextField name, TextField color, TextField category, TextField size, TextArea description, TextField basePrice, TextField quantity, TextField imageSource, Button button, Inventory inv) {

    }
    public static void main(String[] args) {
        launch();
    }
}