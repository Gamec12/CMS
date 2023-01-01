package com.example.cms.AdminSide;

import com.example.cms.Classes.Order;
import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.Map;
import java.util.TreeMap;

public class ListOfOrders extends Application {
    Map<Integer, Order> customers = new TreeMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("List of orders");
        ListView listView = new ListView();
        


    }
}
