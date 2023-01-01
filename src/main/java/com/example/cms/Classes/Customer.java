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
    String address2;
    String zipCode;

    Cart cart = new Cart();

    public Customer(String firstName, String lastName, String mobileNumber, String gender, String emailAddress, String userName, String password, String address1) {
        super(firstName, lastName, mobileNumber, gender, emailAddress, userName, password);
        this.address1 = address1;
        id = nextId;
        nextId++;
        customers.put(id, this);
    }

    public String getAddress1() {
        return address1;
    }

    public Cart getCart() {
        return cart;
    }

}




