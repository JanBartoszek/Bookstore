package Model.Users;

import Model.Orders.Order;

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
}
