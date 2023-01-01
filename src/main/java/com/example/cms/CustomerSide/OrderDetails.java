package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

public class OrderDetails extends Application {
    Customer ahmed;
    public OrderDetails(Customer ahmed){
        this.ahmed=ahmed;
    }
    public static void getItemsOrderDetails( ListView<VBox> listView, Customer customer,Stage stage) {
        for(Product p : customer.getCart().getArr())
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
            HBox hBox = new HBox();
            hBox.setSpacing(30);

            hBox.getChildren().addAll(imageView);
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
    public void start(Stage Stage) {
Stage.setTitle("Order Details");
        ListView x =new ListView();
        getItemsOrderDetails(x,ahmed,Stage);
        VBox box=new VBox(x);
        Label l1=new Label(ahmed.getFirstName() +" "+ahmed.getLastName());
        Label l2=new Label(ahmed.getMobileNumber());
        Label l3=new Label(ahmed.getEmailAddress());
        Label l4=new Label(ahmed.getAddress1());
        Label l5= new Label("Number Of Products  "+String.valueOf(ahmed.getCart().getCount()));
        Label l6= new Label("Total Price  "+String.valueOf(ahmed.getCart().getSum()));
        Button confirm=new Button("Confirm Order");
        box.getChildren().addAll(l1,l2,l3,l4,l5,l6 ,confirm);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE , null , null)));
        Stage.setScene(new Scene(box , 500 , 500) );
        Stage.show();
    }
}
