package com.example.cms;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = GetDefaultPane();


        Text title = new Text("Welcome!");
        title.setFont(Font.font("arial", FontWeight.BOLD, 30));

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
       // stage.getIcons().add(new Image("D:\\Uni\\Semister 3\\OOP\\Projects\\CMS\\src\\main\\java\\com\\example\\cms\\Images\\Icon.jpg"));
        stage.show();


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

    public static void main(String[] args) {
        launch();
    }
}