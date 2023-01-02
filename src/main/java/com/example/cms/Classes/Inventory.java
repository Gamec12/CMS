package com.example.cms.Classes;

import java.io.*;
import java.util.*;
import java.io.ObjectOutputStream;

public class Inventory implements Serializable {
    private int capacity;                               // Determine the maximum number of products
    private int count;                                  // Current count of products in inventory

    private static Map<Integer, Product> products = new HashMap<>();                         // Initialize array of Product type

    public Inventory() throws IOException, ClassNotFoundException {                                // Default constructor (calls parameterized constructor)
        this(1000);
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/Data/inventory.dat"));
            products = (Map<Integer, Product>) objectInputStream.readObject();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public Inventory(int capacity) {                // Constructor with "capacity" parameter
        this.capacity = capacity;
        this.count = 0;
    }

    public void addProduct(Product newProduct) throws IOException {        // Add product to inventory using Product parameter (for gui)
        products.put(newProduct.getItemID(), newProduct);
        save();
        this.count++;
    }

    public void deleteItem(int id) // for gui? // this is very sus
    {
        products.remove(id);
    }

    public static void save() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/Data/inventory.dat"));
        out.writeObject(products);
    }

    public Product getProduct(int id) // to search for an item with a specific id
    {
        return products.get(id); // warning there is a null here
    }

    public void setPrice(int id, double price) {
        products.get(id).setBasePrice(price);
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void setProducts(Map<Integer, Product> products) {
        Inventory.products = products;
    }
}
