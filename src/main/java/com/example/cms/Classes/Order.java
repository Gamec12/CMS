package com.example.cms.Classes;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Order implements Serializable {
    static int id = 1;

    private   ArrayList<Product> items = new ArrayList<>();

    static Map<Integer, ArrayList<Order>> orders = new TreeMap<>();

    private Date date ;
    public Order(Cart c1 , int id) throws IOException, ClassNotFoundException {
        date = new Date();

        if(!c1.isEmpty())
        {
            Load();
            if((orders.containsKey(id)))
            {
                orders.get(id).add(this);
            }
            else
            {
                ArrayList<Order> temp = new ArrayList<>();
                temp.add(this);
                orders.put(id,temp);
            }
            ArrayList<Order> arrayList = orders.get(id);
            arrayList.add(this);

            items.addAll(c1.getArr());
            for(int i = 0 ; i < c1.getArr().size();i++)
            {
                c1.getArr().get(i).setQuantity(c1.getArr().get(i).getQuantity()-1);
                Inventory myInv = new Inventory();
                myInv.getProducts().get(c1.getArr().get(i).getItemID()).setQuantity(c1.getArr().get(i).getQuantity()-1);

                Inventory.save();
            }
            orders.put(id , arrayList);

            save();
        }

    }
    public Order() throws IOException, ClassNotFoundException {
        Load();
    }

    private static void Load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream( new FileInputStream("src/main/Data/Orders.dat"));
        orders = (Map<Integer, ArrayList<Order>>) in.readObject();
    }

    public static void load() throws IOException, ClassNotFoundException {

    }

    public static int getId() {
        return id;
    }

    public static Map<Integer, ArrayList<Order>> getOrders() {
        return orders;
    }

    public ArrayList<Product> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }

    public void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/Data/Orders.dat"));
        out.writeObject(orders);
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", date=" + date +
                '}';
    }
}
