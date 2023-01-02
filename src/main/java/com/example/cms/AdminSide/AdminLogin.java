package com.example.cms.AdminSide;

import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLogin extends Application {
    String password = "12345";

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) {
        GridPane grid = Main.GetDefaultPane();
        Text title = new Text("Enter Admin Password");
        title.setFont(Font.font("arial", FontWeight.BOLD, 30));
        grid.add(title , 0 , 0 ,2,2);
        PasswordField passwordField = new PasswordField();
        Button login = new Main().getDefaultButton("Login");
        login.setPrefHeight(30);
        login.setDefaultButton(true);
        login.setPrefWidth(80);
        login.setAlignment(Pos.CENTER);
        login.setOnAction(e->{
            if(passwordField.getText().equals(password))
            {
                AdminPanel adminPanel = new AdminPanel();
                try {
                    adminPanel.start(stage);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Button back = new Main().getLogOutButton("← Back");
        back.setOnAction(e->{
            Main main = new Main();
            try {
                main.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        HBox hBox = new HBox();
        back.setPrefHeight(30);
        back.setDefaultButton(true);
        back.setPrefWidth(80);
        back.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(back, login);
        hBox.setSpacing(140);
        grid.add(passwordField , 1 , 3);
        grid.add(hBox , 1 ,4);

        stage.setScene( new Scene(grid , 500 , 500));
        stage.show();
    }
}
