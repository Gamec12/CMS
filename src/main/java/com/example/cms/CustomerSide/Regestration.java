package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.HPos;
import javafx.scene.layout.Priority;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Window;

import java.io.IOException;


public class Regestration extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Registration Form");

        GridPane gridPane = createRegistrationFormPane();

        addUIControls(gridPane, stage);

        Scene scene = new Scene(gridPane, 800, 500);

        stage.setScene(scene);
        stage.show();
    }

    private GridPane createRegistrationFormPane() {


        GridPane gridPane = Main.GetDefaultPane();

        gridPane.setAlignment(Pos.CENTER);

        gridPane.setPadding(new Insets(40, 40, 40, 40));


        gridPane.setHgap(10);


        gridPane.setVgap(10);


        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);

        columnOneConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }
    private void addUIControls(GridPane gridPane, Stage stage) {

        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label nameLabel = new Label("First Name: ");
        gridPane.add(nameLabel, 0,1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);

        Label lastNameLabel = new Label("Last Name: ");
        gridPane.add(lastNameLabel, 0,2);

        TextField lastnameField = new TextField();
        lastnameField.setPrefHeight(40);
        gridPane.add(lastnameField, 1,2);

        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0, 3);

        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 3);
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 4);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 4);

        Label username = new Label("username :");
        gridPane.add(username, 0, 5);

        TextField usernamefield = new TextField();
        usernamefield.setPrefHeight(40);
        gridPane.add(usernamefield, 1, 5);

        Label address = new Label("address :");
        gridPane.add(address, 0, 6);

        TextField addressfield = new TextField();
        addressfield.setPrefHeight(40);
        gridPane.add(addressfield, 1, 6);

        Label gender = new Label("gender:");
        gridPane.add(gender, 0, 7);

        TextField genderfield = new TextField();
        genderfield.setPrefHeight(40);
        gridPane.add(genderfield, 1, 7);

        Label mobileNumber = new Label("mobileNumber :");
        gridPane.add(mobileNumber, 0, 8);

        TextField mobileNumberfield = new TextField();
        mobileNumberfield.setPrefHeight(40);
        gridPane.add(mobileNumberfield, 1, 8);

        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 9, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        submitButton.setOnAction(e->{


                if(nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your firstName");
                    return;

                }
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your email");
                    return;
                }

                if (lastnameField.getText().isEmpty())
                {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your lastName");
                    return;
                }

                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                    return;
                }

                if (usernamefield.getText().isEmpty())
                {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a username");
                    return;
                }

                if (addressfield.getText().isEmpty())
                {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a address");
                    return;
                }

                if (genderfield.getText().isEmpty())
                {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your gender");
                    return;
                }

                if (mobileNumberfield.getText().isEmpty())
                {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter mobileNumber");
                    return;
                }
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                if (emailField.getText().matches(regex))
                {
                    System.out.println(" ");
                }
                else
                {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a valid Email");
                    return;
                }
            try {
                Customer customer = new Customer(nameField.getText(),lastnameField.getText(),mobileNumberfield.getText(), genderfield.getText(), emailField.getText(), usernamefield.getText(), passwordField.getText(), addressfield.getText());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main main = new Main();
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + nameField.getText());
            try {
                main.start(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });



    }
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}





