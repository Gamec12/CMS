package com.example.cms.Classes;

public class Product {
    private int itemID;
    private String Name;
    private String color;
    private String category;
    private String size;
    private String description;

    private String ImageSource;
    private double basePrice;
    public Product(int itemID,String Name, String color, String category, String size, String description, double basePrice) {
        this.itemID = itemID;
        this.color = color;
        this.category = category;
        this.size = size;
        this.description = description;
        this.basePrice = basePrice;
        this.Name = Name;
        discountPrice = 0; // it won't be discounted by default
    }

    private double discountPrice;

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
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

    @Override
    public String toString() {
        return "Product{" +
                "itemID=" + itemID +
                ", Name='" + Name + '\'' +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
