package com.example.cms;

import com.example.cms.Classes.Inventory;
import com.example.cms.Classes.Product;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class cart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("cart");
        String p =new String("tomato");
        ListView h=new ListView();
                h.getItems().add(p.toString());
                System.out.println(p.toString());
        Label l1=new Label("Number of Products:");
        Label l2=new Label("");
        Label l3=new Label("Total Price:");
        Label l4=new Label("");
        Button b=new Button("Back");
        VBox box= new VBox();
        box.getChildren().addAll(h,l1,l2,l3,l4,b);
        box.setBackground(new Background(new BackgroundFill(Color.WHITE , null , null)));
        stage.setScene(new Scene(box , 500 , 500) );
        stage.show();
    }
}
