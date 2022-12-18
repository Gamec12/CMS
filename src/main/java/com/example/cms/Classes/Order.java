package com.example.cms.Classes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    static int id = 1;
    public static ArrayList<Order> orders = new ArrayList<>() ;
    public  ArrayList<Product> items = new ArrayList<>();

    private Date date ;
    Order(Cart c1 , String userName) throws IOException {
        if(!c1.isEmpty())
        {
            File file = new File("orders.json");

            FileWriter out = new FileWriter(file);
            date = new Date();
            Gson json = new Gson();
            json.toJson(c1.getArr() ,out);
            out.close();

            System.out.println("Ordered sucessfully");
        }

    }

    public static void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream( new FileInputStream("Orders.dat") );
        orders = (ArrayList<Order>) in.readObject();
    }
}
