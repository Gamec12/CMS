package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Product;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class Cart extends Application {
    Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public static void getItemsCart(ListView<VBox> listView, Customer customer, Stage stage) {
        for (Product p : customer.getCart().getArr()) {
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(10));

            Button button = Main.getRedButton("X");
            button.setPrefSize(70, 20);

            button.setOnAction(e -> {
                customer.getCart().removeFromCart(p.getItemID());
                Cart cart1 = new Cart(customer);
                cart1.start(stage);
            });
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

            hBox.getChildren().addAll(imageView, button);
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
    public void start(Stage stage) {
        ListView x = new ListView();
        getItemsCart(x, customer, stage);
        Button b = Main.getRedButton("← Back");
        b.setOnAction(e -> {
            CustomerPanel c1 = null;
            try {
                c1 = new CustomerPanel(customer);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            c1.start(stage);
        });
        Button b1 = Main.getDefaultButton("Checkout");
        b1.setOnAction(e -> {
            if (customer.getCart().getArr().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Cart is Empty");
                alert.showAndWait();
                return;
            }
            OrderDetails l = new OrderDetails(customer);
            l.start(stage);
        });
        VBox box = new VBox(x);
        HBox buttons = new HBox();
        buttons.getChildren().addAll(b, b1);
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(10);
        box.setSpacing(15);
        box.getChildren().addAll(buttons);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        stage.setScene(new Scene(box, 500, 500));
        stage.show();
    }
}
