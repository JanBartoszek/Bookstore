package Model.Users;

import Model.Orders.Order;
import Model.Products.Product;

import java.util.HashMap;

public class User {

    private String fullname;
    private boolean isAdmin;
    private Order currentOrder;
    private HashMap<Integer, Order> orderHistory;

    public User(String fullname, boolean isAdmin) {
        this.fullname = fullname;
        this.isAdmin = isAdmin;
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

    public void createNewOrder(){
        currentOrder = Order.createNewOrder();
    }

    public void addToCurrentOrder(Product product, int quantity){
        currentOrder.addToBasket(product, quantity);
    }

}
