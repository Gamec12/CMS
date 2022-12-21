package com.example.cms.Classes;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Customer extends User implements Serializable {
    public static int nextId = 1; // to know the next id sequentially
    Cart cart;
    static Map<Integer, Customer> customers = new TreeMap<>();

    public Customer(String firstName, String lastName, String mobileNumber, String gender, String emailAddress, String userName, String password) {
        super(firstName, lastName, mobileNumber, gender, emailAddress, userName, password);

    }

    public static void addCustomer() {
        customers.put(nextId, register());
        nextId++;

    }

    public static Customer register() {
        Scanner input = new Scanner(System.in);
        String firstName, lastName, mobileNumber, gender, emailAddress, userName, password;
        System.out.println("\n>>>  Enter your first name");
        firstName = input.next();
        System.out.println("\n>>>  Enter your last name");
        lastName = input.next();
        System.out.println("\n>>>  Enter your email address");
        emailAddress = input.next();
        System.out.println("\n>>>  Enter your mobile number");
        mobileNumber = input.next();
        System.out.println("\n>>>  Enter your gender");
        gender = input.next();
        System.out.println("\n>>>  Enter your username");
        userName = input.next();
        System.out.println("\n>>>  Enter your password");
        password = input.next();

        System.out.println("\n>>>  Account successfully created  <<<");
        System.out.println(">>>  Press [ 1 ] to login  <<<\n");

        return new Customer(firstName, lastName, mobileNumber, gender, emailAddress, userName, password);
    }

//    public static boolean checkCustomer(ListOfCustomers l1, String userName, String password) {
//        for (Customer a : l1.getCustomers().values()) // checks if the user is a customer
//        {
//            if (password.equals(a.getPassword()) && userName.equals(a.getUserName())) // checks if password and username match
//            {
//                System.out.println("welcome " + a.getFirstName());
//                return true;
//            }
//        }
//        return false;
//    }

    public static void save() { // using text file

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Data/Customers.bin"));
            out.writeObject(customers);
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void display() {
        System.out.println(customers);
    }

    public Map<Integer, Customer> getCustomers() {
        return customers;
    }

    public static void login(Inventory inventory) {
        try {
            boolean check = false;
            File file = new File("src/Data/Customers.txt");
            Scanner in = new Scanner(file); // not useful
            Scanner input = new Scanner(System.in);
            String userName, password;

            System.out.println("\n>>>  Enter your username  <<<");
            userName = input.next();
            System.out.println("\n>>>  Enter your password  <<<");
            password = input.next();

            for (Customer c : customers.values()) {
                if (c.getUserName().equals(userName) && c.getPassword().equals(password)) {
                    customerLoop(inventory, userName);
                    check = true;
                }
            }
            if(!check)
            {
                System.err.println("Invalid username or password");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void customerLoop(Inventory inventory, String userName) {
        Scanner input = new Scanner(System.in);
        Cart cart = new Cart();
        String choice;
        printCustomerOptions();
        choice = input.nextLine().toLowerCase();

        while (true) {
            // Handle customer login
            switch (choice) {
                case "1":
                    inventory.display();
                    break;
                case "2":
                    System.out.println("Enter the id of the item you want to add to your cart");
                    int id = Validations.inputNum();
                    cart.addToCart(id, inventory);

                    break;
                case "3":
                    cart.displayCart();

                    break;
                case "4":
                    try {
                        Order order = new Order(cart, userName);

                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Invalid entry");
            }
            System.out.println();
            System.out.println();
            printCustomerOptions();
            choice = input.nextLine().toLowerCase();
        }
    }

    private static void printCustomerOptions() {
        System.out.println(" [ 1 ] - to view all clothing items");
        System.out.println(" [ 2 ] - add item to cart");
        System.out.println(" [ 3 ] - view items in cart ");
        System.out.println(" [ 4 ] - Order items in cart");
        System.out.println(" [ e ] - press e to log out");
        System.out.println(">>>  Enter the respective instruction to execute an action  <<<");
    }

    public static void load()
    {
        File file = new File("src/Data/Customers.bin");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            customers = (Map<Integer, Customer>) in.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}



