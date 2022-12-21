package com.example.cms;

import com.example.cms.AdminPannel;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLogin extends Application {

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) {
        GridPane grid = Main.GetDefaultPane();
        Text title = new Text("Enter your password!");
        title.setFont(Font.font("arial", FontWeight.BOLD, 30));
        grid.add(title , 0 , 0 ,2,2);
        PasswordField passwordField = new PasswordField();
        Button login = new Button("Log in");
        login.setOnAction(e->{
            if(passwordField.getText().equals("12345"))
            {
                AdminPannel adminPannel= new AdminPannel();
                try {
                    adminPannel.start(stage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        grid.add(passwordField , 1 , 3);
        grid.add(login , 1 , 4);

        stage.setScene( new Scene(grid , 500 , 500));
        stage.show();
    }
}
