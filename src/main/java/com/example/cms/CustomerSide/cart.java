package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Product;
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

public class cart extends Application {
    Customer customer;
    public cart(Customer customer) {
        this.customer = customer;
    }
    public static void getItemsCart( ListView<VBox> listView, Customer customer,Stage stage) {
        for(Product p : customer.getCart().getArr())
        {
            VBox vBox = new VBox();
            vBox.setSpacing(10);
            vBox.setPadding(new Insets(10));

            Button button = new Button("Remove From Cart");
            button.setOnAction(e->{
                customer.getCart().removeFromCart(p.getItemID());
                cart cart1=new cart(customer);
                cart1.start(stage);
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

            System.out.println(p.toString());

        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        stage.setTitle("cart");
 ListView x =new ListView();
 getItemsCart(x, customer,stage);
        Button b=new Button("Back");
        b.setOnAction(e->{
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
        Button b1 =new Button("Checkout");
        b1.setOnAction(e->{
            if(customer.getCart().getArr().size()==0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Cart is Empty");
                alert.showAndWait();
                return;
            }
            OrderDetails l=new OrderDetails(customer);
            l.start(stage);
        });
        VBox box= new VBox(x);
        box.getChildren().addAll(b,b1);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE , null , null)));
        stage.setScene(new Scene(box , 500 , 500) );
        stage.show();
    }
}
