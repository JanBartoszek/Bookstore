package Controller;

import Model.Orders.Order;
import Model.Products.Product;
import Model.Users.User;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {

    public static List<Product> products = new ArrayList<Product>();
    List users = new ArrayList<User>();
    List orders = new ArrayList<Order>();

    private void addBook(Product product){
        products.add(product);
    }

}
