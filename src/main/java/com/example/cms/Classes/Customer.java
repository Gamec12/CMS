package com.example.cms.Classes;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Customer extends User implements Serializable {
    public static int nextId = 1; // to know the next id sequentially
    private static int id;
    static Map<Integer, Customer> customers = new TreeMap<>();
    String address1;

    Cart cart = new Cart();

    public Customer(String firstName, String lastName, String mobileNumber, String gender, String emailAddress, String userName, String password, String address1) throws IOException, ClassNotFoundException {
        super(firstName, lastName, mobileNumber, gender, emailAddress, userName, password);
        try{
            load();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        this.address1 = address1;
        id = nextId;
        nextId++;
        customers.put(id, this);
        save();
    }

    public String getAddress1() {
        return address1;
    }

    public Cart getCart() {
        return cart;
    }

    public static void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/Data/customers.dat"));
        customers = (Map<Integer, Customer>) in.readObject();
    }

    public static void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/Data/customers.dat"));
        out.writeObject(customers);
    }

    public static int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Customer.nextId = nextId;
    }

    public static void setId(int id) {
        Customer.id = id;
    }

    public static Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(Map<Integer, Customer> customers) {
        Customer.customers = customers;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}




