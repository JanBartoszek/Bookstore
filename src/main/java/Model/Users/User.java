package Model.Users;

import Controller.Bookstore;
import Model.Orders.Order;
import Model.Products.Product;

import java.util.HashMap;

public class User {

    private String fullname;
    private boolean isAdmin;
    private Order currentOrder;
    private Bookstore bookstore;
    private HashMap<Integer, Order> orderHistory = new HashMap<>();

    public User(String fullname, boolean isAdmin, Bookstore bookstore) {
        this.fullname = fullname;
        this.isAdmin = isAdmin;
        this.bookstore = bookstore;
    }

    public String getFullname() {
        return fullname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public HashMap<Integer, Order> getOrderHistory() {
        return orderHistory;
    }

    public void createNewOrder() {
        currentOrder = Order.createNewOrder();
    }

    public void addToCurrentOrder(Product product, int quantity) {
        currentOrder.addToBasket(product, quantity);
    }

    public void removeFromCurrentOrder(Product product, int quantity) {
        currentOrder.removeFromBasket(product, quantity);
    }

    public void commitCurrentOrder() {
        bookstore.handleUserOrder(currentOrder);
        orderHistory.put(generateId(), currentOrder);
        currentOrder.setFinished(true);
        currentOrder = null;
    }

    private int generateId() {
        return orderHistory.size() + 1;
    }

    public void addProduct(Product product) {
        if (!isAdmin) {
            return;
        }
        Bookstore.products.add(product);
    }

    public void removeProduct(Product product) {
        if (!isAdmin) {
            return;
        }
        Bookstore.products.remove(product);
    }

}
