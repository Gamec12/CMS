package com.example.cms.Classes;

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
}
