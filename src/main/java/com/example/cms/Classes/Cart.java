package com.example.cms.Classes;
import java.util.ArrayList;

public class Cart {
private ArrayList<Product> arr;
private int count;

public Cart()
{
    count = 0;
    arr = new ArrayList<>();
}

public Cart(Product A)
{
    count = 1;
    arr = new ArrayList<>(); // sum
    arr.add(A);

}
public void addToCart(int id, Inventory myInv)
{
    Product test = getProduct(id , myInv);
    if(test != null) // making sure that there is something with that id
    {
        arr.add(test);
        System.out.println("Added sucessfully");
        count++;
    }
    else
    {
        System.out.println("Invalid ID");
    }


}

public void removeFromCart(int id)
{
    for(int i = 0 ; i< arr.size();i++)
    {
        if(arr.get(i).getItemID() == id)
        {
            arr.remove(i);
        }
    }
}

public void clearCart()
{
    arr.clear();
}
public void displayCart()
{
    if(count == 0)
    {
        System.out.println("cart is empty");
        return;
    }
    double price = 0;
    System.out.println("There are " + count + " items in the cart");
    for(int i = 0 ; i< arr.size();i++)
    {
        System.out.println("Order " + i + " is ");
        System.out.println(arr.get(i).getItemID());
        System.out.println(arr.get(i).getName());
        System.out.println(arr.get(i).getColor());
        System.out.println(arr.get(i).getCategory());
        System.out.println(arr.get(i).getSize());
        System.out.println(arr.get(i).getDescription());
        System.out.println(arr.get(i).getBasePrice());
        price += arr.get(i).getBasePrice();

    }
    System.out.println( "Total price "+ price);
}

    public  Product getProduct(int id , Inventory I) // to search for an item with a specific id
    {
        return I.getProduct(id);
    }

    public ArrayList<Product> getArr() {
        return arr;
    }

    public  boolean isEmpty()
    {
        return arr.isEmpty();
    }

    public void addProduct(Product p) {
        arr.add(p);
    }
}
