package com.example.cms.Classes;

import java.io.*;
import java.util.*;
import java.io.ObjectOutputStream;

public class Inventory implements Serializable {
    private int capacity;                               // Determine the maximum number of products
    private int count;                                  // Current count of products in inventory



    private Map<Integer , Product> products = new HashMap<>();                         // Initialize array of Product type

    java.io.File file = new java.io.File("src/main/Data/Inventory.json");


    public Inventory() throws IOException, ClassNotFoundException {                                // Default constructor (calls parameterized constructor)
        this(1000);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/Data/inventory.dat"));
        products = (Map<Integer, Product>) objectInputStream.readObject();


    }

    public Inventory(int capacity) {                // Constructor with "capacity" parameter
        this.capacity = capacity;
        this.count = 0;

    }

    public void addProduct() {                          // Add product to inventory using Scanner
        Scanner input = new Scanner(System.in);

        int id = 0, quantity = 0;
        String category , size , color , description;
        double basePrice = 0;
        boolean check = true;// to be able to check if id quantity and price are numbers
        System.out.println("Enter the item id");
        String name;


        while (check) // this while loop will make sure the user enter an integer for id
        {
            try
            {
                id =input.nextInt();

                    if(products.containsKey(id)) // to check for duplicate ID
                    {
                        System.out.println("ID already exists");
                        continue;
                    }
                    else {
                        check = false;
                    }
            }
            catch (InputMismatchException ex) // handling the exception
            {
                System.out.println("enter a number please");
                input.nextLine(); // to
            }
        }

        System.out.println("Enter the name of the product");
        name = input.next();

        System.out.println("Enter the color of the item");
        color = input.next(); // storing the color

        System.out.println("Enter the category of the item");
        category = input.next();

        System.out.println("Enter the size ");
        size = input.next();

        System.out.println("Enter item description");
        input.nextLine(); // to discard input?
        description = input.nextLine();

        check = true;
        // to make sure basePrice is a double
        System.out.println("Enter the base price");
        while (check) // this while loop will make sure the user enter an integer for id
        {
            try
            {
                basePrice =input.nextInt();
                check = false;
            }
            catch (InputMismatchException ex) // handling the exception
            {
                System.out.println("enter a number please");
                input.nextLine(); // to

            }
        }
        // to make sure quantity is integer
        System.out.println("Enter the quantity");
        check = true;
        while (check) // this while loop will make sure the user enter an integer for id
        {
            try
            {
                quantity = input.nextInt();
                check = false;
            }
            catch (InputMismatchException ex) // handling the exception
            {
                System.out.println("enter a number please");
                input.nextLine(); // to
            }
        }
        Product a = new Product(id, name ,color, category, size, description, basePrice , 999);

         products.put(id,a);
        count++;
    }

    public void addProduct(Product newProduct) throws IOException {        // Add product to inventory using Product parameter (for gui)
        products.put(newProduct.getItemID() , newProduct);
        save();
        this.count++;
    }

    public void deleteItem() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the id of the item to be deleted");
        int id = Validations.inputNum(); // enter the id of the item to be deleted
        if(products.containsKey(id))
        {
            products.remove(id);
            save();
        }

    }

    public void deleteItem(int id) // for gui? // this is very sus
    {

        products.remove(id);


    }



    public void display()
    {


        for(Product p : products.values())
        {
            System.out.println(p); //calls to s

        }
    }



    public void save() throws IOException {                             // Saves inventory object to JSON
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/Data/inventory.dat"));
        out.writeObject(products);
    }

    public  Product getProduct(int id) // to search for an item with a specific id
    {

        return products.get(id); // warning there is a null here
    }

    public void setPrice(int id, double price)
    {
        products.get(id).setBasePrice(price);

    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

}