package com.example.cms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomersView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane gridPane = Main.GetDefaultPane();
        Text text = new Text("Customers");
        text.setFont(Font.font("arial", FontWeight.BOLD, 30));
        gridPane.add(text, 0 ,0 ,2,2);
        ListView listView = new ListView();
        gridPane.add(listView , 0 , 3);

        listView.getItems().addAll("Test1" , "Test2" , "Test3");

        stage.setScene(new Scene(gridPane));
        stage.show();

    }
}
