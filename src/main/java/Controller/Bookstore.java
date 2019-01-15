package Controller;

import Model.Orders.Order;
import Model.Orders.OrderDetail;
import Model.Products.Product;
import Model.Users.User;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {

    public static List<Product> products = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    public List<Order> orders = new ArrayList<>();

    public void handleUserOrder(Order order) {

        int index;
        int calculatedStock;

        for (OrderDetail detail : order.getOrderedProducts()) {
            index = Bookstore.products.indexOf(detail.getProduct());
            calculatedStock = calculateStockAfterRemove(detail, index);
            if (calculatedStock < 0) {
                Bookstore.products.get(index).setStock(0);
            }
            Bookstore.products.get(index).setStock(calculatedStock);
        }
    }

    private int calculateStockAfterRemove(OrderDetail detail, int index) {
        int currentBookstoreStock;
        int orderedQuantity;
        int calculatedStock;

        currentBookstoreStock = Bookstore.products.get(index).getStock();
        orderedQuantity = detail.getQuantity();
        calculatedStock = currentBookstoreStock - orderedQuantity;
        return calculatedStock;
    }

    public void createUser(String fullname, boolean isAdmin, Bookstore bookstore) {
        new User(fullname, isAdmin, bookstore);
    }
}
