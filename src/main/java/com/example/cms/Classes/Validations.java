package com.example.cms.Classes;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validations {
    public static double inputDouble() // a function that checks if the input is an int or not
    {
        Scanner input = new Scanner(System.in);
        boolean check = true;
        double num = 12121314;
        while (check) // this while loop will make sure the user enter an integer for id
        {
            try {
                num = input.nextDouble();
                check = false;
            } catch (InputMismatchException ex) {
                System.out.println("Enter a number please");
                input.next();

            }

        }
        return num;
    }

    public static int inputNum() // a function that checks if the input is an int or not
    {
        Scanner input = new Scanner(System.in);
        boolean check = true;
        int num = 12121314;
        while (check) // this while loop will make sure the user enter an integer for id
        {
            try {
                num = input.nextInt();
                check = false;
            } catch (InputMismatchException ex) {
                System.out.println("Enter a number please");
                input.next();

            }
        }
        return num;
    }

    public static boolean isInt(TextField input)
    {
        try
        {
            int num = Integer.parseInt(input.getText());
            return true;
        }
        catch (NumberFormatException ex)
        {
            input.selectAll();
            input.setText("Enter a number");
            input.setStyle("-fx-text-fill: red");


            return false;
        }
    }
    public static boolean isDouble(TextField input)
    {
        try
        {
            double num = Double.parseDouble(input.getText());
            return true;
        }
        catch (NumberFormatException ex)
        {
            input.selectAll();
            input.setText("Enter a number");
            input.setStyle("-fx-text-fill: red");
            return false;
        }
    }
    public  static boolean idExists(Inventory inv , int id)
    {
        return inv.getProducts().containsKey(id);
    }

}
