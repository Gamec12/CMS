package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Order;
import com.example.cms.Classes.Product;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderDetails extends Application {
    Customer customer;

    public OrderDetails(Customer customer) {
        this.customer = customer;
    }

    public static void getItemsOrderDetails(ListView<VBox> listView, Customer customer, Stage stage) {
        for (Product p : customer.getCart().getArr()) {
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(10));

            ImageView imageView;
            try {
                imageView = new ImageView(new Image(p.ImageSource));

                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
            } catch (IllegalArgumentException ex) {
                imageView = new ImageView(new Image("https://github.com/Gamec12/CMS/blob/b6a4bf0f77234d7e5a406d1e7b6a4a62ed3788fb/src/main/java/com/example/cms/Images/Icon.jpg"));
            }
            HBox hBox = new HBox();
            hBox.setSpacing(30);

            hBox.getChildren().addAll(imageView);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            Text item = new Text(
                    "[" + p.getItemID() + "] " + p.getCategory()
                            + "'s " + p.getSubCategory()
                            + "\n\n" + p.getName()
                            + "\n" + p.getDescription()
                            + "\n" + p.getSize()
                            + "  -  " + p.getColor()
                            + "\nCost:  $" + p.getBasePrice()
            );
            vBox.getChildren().addAll(hBox, item);
            listView.getItems().add(vBox);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage Stage) {
        ListView x = new ListView();
        getItemsOrderDetails(x, customer, Stage);
        VBox box = new VBox(x);
        Label l1 = new Label(customer.getFirstName() + " " + customer.getLastName());
        Label l2 = new Label("Phone:  " + customer.getMobileNumber());
        Label l3 = new Label("Email:  " + customer.getEmailAddress());
        Label l4 = new Label(customer.getAddress1());
        Label l5 = new Label("Product count:  " + String.valueOf(customer.getCart().getCount()));
        Label l6 = new Label("Total price:  $" + String.valueOf(customer.getCart().getSum()));
        Button confirm = Main.getDefaultButton("Confirm Order");
        confirm.setStyle("-fx-background-color: #33bb33");
        confirm.setOnAction(e -> {
            try {
                Order order = new Order(customer.getCart(), customer.getId());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            customer.getCart().getArr().clear();
            customer.getCart().setCount(0);
            customer.getCart().setSum(0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Order Confirmed");
            alert.setTitle("Thank You");
            alert.setContentText("Your order will arrive soon");
            alert.showAndWait();
            try {
                CustomerPanel customerPanel = new CustomerPanel(customer);
                customerPanel.start(Stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }


        });
        Button back = Main.getDefaultButton("??? Back");
        back.setOnAction(e -> {
            Cart cart1 = new Cart(customer);
            cart1.start(Stage);
        });
        HBox hBox = new HBox(back, confirm);
        hBox.setSpacing(10);
        box.setPadding(new Insets(30));
        box.getChildren().addAll(l1, l2, l3, l4, l5, l6, hBox);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        Stage.setScene(new Scene(box, 500, 500));
        Stage.show();
    }
}
