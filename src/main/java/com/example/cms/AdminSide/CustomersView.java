package com.example.cms.AdminSide;

import com.example.cms.AdminSide.AdminPannel;
import com.example.cms.Classes.Customer;
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

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class CustomersView extends Application {
    Map<Integer, Customer> customers = new TreeMap<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/Data/Customers.dat"));
        customers = (Map<Integer, Customer>) in.readObject();
        GridPane gridPane = Main.GetDefaultPane();
        Text text = new Text("Customers");
        text.setFont(Font.font("arial", FontWeight.BOLD, 30));
        gridPane.add(text, 0 ,0 ,2,2);
        Button button = Main.getDefaultButton("Back");
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
        listView.setMinWidth(500);
        gridPane.add(listView , 0 , 4);
        String customer = "";
        int count = 1;
        for(Customer c : customers.values())
        {

             customer= "Customer " + count + ":\n" + "Username: " + c.getUserName() + "\nFirst name: " + c.getFirstName() + "\nLast name: " + c.getLastName()+"\nEmail: "+ c.getEmailAddress() + "\nAddress : " + c.getAddress1() +"\nMobile number: " +  c.getMobileNumber() + "\nGender: " + c.getGender() ;
            listView.getItems().add(customer);
            count++;
        }
        stage.setTitle("Customers");
        stage.setScene(new Scene(gridPane));
        stage.show();

    }
}
