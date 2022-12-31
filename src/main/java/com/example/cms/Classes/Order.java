package com.example.cms.Classes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    static int id = 1;
    private static ArrayList<Order> orders = new ArrayList<>() ;
    private   ArrayList<Product> items = new ArrayList<>();

    private Date date ;
    public Order(Cart c1 , String userName) throws IOException, ClassNotFoundException {
        date = new Date();
        if(!c1.isEmpty())
        {
            Load();
            items.addAll(c1.getArr());
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Orders.dat"));
            out.writeObject(orders);
        }

    }
    public Order() throws IOException, ClassNotFoundException {
        Load();
    }

    private static void Load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream( new FileInputStream("Orders.dat"));
        orders = (ArrayList<Order>) in.readObject();
    }

    public static void load() throws IOException, ClassNotFoundException {

    }

    public static int getId() {
        return id;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }
}
