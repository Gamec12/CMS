package com.example.cms.CustomerSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Main;
import javafx.application.Application;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.TreeMap;

public class Login extends Application {
    Map<Integer, Customer> customers = new TreeMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/Data/Customers.dat"));
        customers = (Map<Integer, Customer>) in.readObject();
        in.close();
        primaryStage.setTitle("CMS");

        GridPane gridPane = createRegistrationFormPane();

        addUIControls(gridPane, primaryStage);

        Scene scene = new Scene(gridPane, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createRegistrationFormPane() {

        GridPane gridPane = Main.GetDefaultPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane, Stage stage) {

        Label headerLabel = new Label("Client Login");
        headerLabel.setFont(Font.font("Arial", 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        Label nameLabel = new Label("Username:");
        gridPane.add(nameLabel, 0, 1);

        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);
        Button submitButton = new Button("Log in");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        submitButton.setOnAction(e -> {
            if (passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a password");
                return;
            }
            if (usernameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a username");
                return;
            }
            for (Customer c : customers.values()) {
                if (c.getUserName().equals(usernameField.getText()) && c.getPassword().equals(passwordField.getText())) {
                    try {
                        CustomerPanel cp = new CustomerPanel(c);
                        cp.start(stage);

                    } catch (IOException | ClassNotFoundException ioException) {
                        System.out.println(e);
                    }
                    return;
                }
            }
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Wrong username or password");
        });
        gridPane.add(submitButton, 0, 9, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            try {
                Main m = new Main();
                m.start(stage);
            } catch (IOException ioException) {
                System.out.println(e);
            }
        });
        gridPane.add(backButton, 0, 9);
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