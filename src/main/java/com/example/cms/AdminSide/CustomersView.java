package com.example.cms.AdminSide;

import com.example.cms.AdminSide.AdminPannel;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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
        Button button = new Button("Back");
        gridPane.add(button , 0,3);
        button.setOnAction(e->{
            AdminPannel adminPannel = new AdminPannel();
            try {
                adminPannel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        ListView listView = new ListView();
        gridPane.add(listView , 0 , 4);

        listView.getItems().addAll("Test1" , "Test2" , "Test3");

        stage.setScene(new Scene(gridPane));
        stage.show();

    }
}
