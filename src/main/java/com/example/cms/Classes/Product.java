package com.example.cms.Classes;

import java.io.Serializable;

public class Product implements Serializable {
    private int itemID;
    private String Name;
    private String color;
    private String category;
    private String size;
    private String description;

    private int quantity;
    private String subCategory;


    public String ImageSource;
    private double basePrice;
    public Product(int itemID,String Name, String color, String category, String size, String description, double basePrice , int quantity, String ImageSource , String subCategory) {
        this.itemID = itemID;
        this.color = color;
        this.category = category;
        this.size = size;
        this.description = description;
        this.basePrice = basePrice;
        this.Name = Name;
        this.quantity =quantity;
        this.ImageSource = ImageSource;
        this.subCategory = subCategory;
        

    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice ;
    }

    public void display()
    {
        System.out.println("Item id is " + itemID);

    }

    public String getName() {
        return Name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return
                "itemID=" + itemID +
                ", Name : " + Name + '\'' +
                ", color : " + color + '\'' +
                ", category : '" + category + '\'' +
                        ", sub-Category"+ subCategory +
                ", size : " + size + '\'' +
                ", description : " + description + '\'' +
                ", basePrice : " + basePrice +
                "quantity : " + quantity +
                '}';
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
