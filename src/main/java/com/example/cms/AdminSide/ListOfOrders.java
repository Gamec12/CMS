package com.example.cms.AdminSide;

import com.example.cms.Classes.Customer;
import com.example.cms.Classes.Order;
import com.example.cms.Classes.Product;
import com.example.cms.Main;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ListOfOrders extends Application {
    Map<Integer, ArrayList<Order>> orders = new TreeMap<>();
    Map<Integer, Customer> customers = new TreeMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        int counter = 1;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/Data/orders.dat"));
            orders = (Map<Integer, ArrayList<Order>>) in.readObject();
        }
        catch (Exception e){
            System.out.println("No orders yet");
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/Data/Customers.dat"));
            customers = (Map<Integer, Customer>) in.readObject();
        }
        catch (Exception e){
            System.out.println("No customers yet");
        }
        ListView listView = new ListView();
        for (Map.Entry<Integer, ArrayList<Order>> p : orders.entrySet()) {
            for(int i = 0 ; i < p.getValue().size();i++)
            {
                String text = "Order " + counter + " :\n";
                int count = 1;
                for(Product product :  p.getValue().get(i).getItems())
                {
                    text+= "Item " + count + " : \n";
                    text+= "Customer : " + customers.get(p.getKey()).getUserName() + "(" + customers.get(p.getKey()).getEmailAddress()  +")\n";
                    text+= "ID: " + "["+ String.valueOf( product.getItemID()) +"] \nCategory "  + product.getCategory() + ", Subcategory " + product.getSubCategory()+ "\nName: " + product.getName() + "\nCost:  $" + product.getBasePrice() + "\n" + "Size: " +product.getSize() + " \n";
                    count++;
                }
            {

            }
                listView.getItems().add(text);
                counter++;

            }
            System.out.println("HELP");
        }
        Button back = Main.getDefaultButton("Back");
        back.setOnAction(e->{
            AdminPanel adminPanel = new AdminPanel();
            try {
                adminPanel.start(stage);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY , null , null)));
        vBox.getChildren().addAll(listView,back);
        VBox.setMargin(back , new Insets(10 , 0 ,0 ,5));
        stage.setScene(new Scene(vBox, 550, 450));
        stage.show();
        


    }
}
