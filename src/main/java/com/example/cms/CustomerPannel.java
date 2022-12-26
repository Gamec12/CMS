package com.example.cms;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CustomerPannel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane = Main.GetDefaultPane();

        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        VBox vBox = new VBox();
        HBox filter = new HBox();



//        Button orders = new Button("Orders");
        Button cart = new Button("Cart"); // maybe image in the future
        Button profile = new Button("Profile");
        hBox.getChildren().addAll(profile,cart);
        borderPane.setTop(hBox);
        HBox.setMargin(profile, new Insets(0,15,0,0));


        stage.setScene(new Scene(borderPane, 500, 500));
        stage.show();
    }
}
