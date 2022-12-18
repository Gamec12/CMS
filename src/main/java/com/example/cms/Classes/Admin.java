package com.example.cms.Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    public void addProduct(Inventory myInv) {
        myInv.addProduct();
        myInv.save();
    }

    public void deleteProduct(Inventory myInv) {
        myInv.deleteItem();

    }

    public static void login(Inventory inventory) {
        try {
            File file = new File("src/Data/Admin.txt");
            Scanner in = new Scanner(file);
            Scanner input = new Scanner(System.in);
            String password;

            System.out.println("\n>>>  ADMIN: Enter your password  <<<");
            password = input.next();

            if (password.equals(in.nextLine())) {
                System.out.println("\n>>>  ADMIN: WELCOME!  <<<\n");
                Admin.adminLoop(inventory);
            } else {
                System.out.println("\n>>>  ERROR: Login failed - incorrect password  <<<\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void adminLoop(Inventory inventory) // will exit out of it if he wants to
    {
        int id;
        double price;
        Admin admin = new Admin();
        System.out.println("""
                1: add Item\s
                2: delete item
                3: change price of an item\s
                4 see orders\s
                10: log out""");
        int num = Validations.inputNum();
        while (num != 10) {
            if (num == 1) {
                admin.addProduct(inventory);
            } else if (num == 2) {
                inventory.deleteItem();
            } else if (num == 3) {
                System.out.println("enter the id of the item you want to delete");
                id = Validations.inputNum();
                System.out.println("Enter the new price");
                price = Validations.inputDouble();
                inventory.setPrice(id, price);
            }
            System.out.println("""
                    1: add Item\s
                    2: delete item
                    3: change price of an item\s
                    4 see orders\s
                    10: log out""");
            num = Validations.inputNum();
        }

    }


}
