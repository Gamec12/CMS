package com.example.cms.AdminSide;

import com.example.cms.Classes.Order;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ListOfOrders extends Application {
    Map<Integer, ArrayList<Order>> orders = new TreeMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/Data/Orders.dat"));
        orders = (Map<Integer, ArrayList<Order>>) in.readObject();
        stage.setTitle("List of orders");
        ListView listView = new ListView();
        for (Map.Entry<Integer, ArrayList<Order>> entry : orders.entrySet()) {
            for(int i = 0 ; i < entry.getValue().size();i++)
            {
                listView.getItems().add(entry.getValue().get(i).toString());
            }
            System.out.println("HELP");
        }


        stage.setScene(new Scene(listView, 550, 500));
        stage.show();
        


    }
}
