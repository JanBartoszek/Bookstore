package Model.Orders;

import Controller.Bookstore;
import Model.Products.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderDetail> orderedProducts = new ArrayList<OrderDetail>();
    private boolean isFinished;

    public static Order createNewOrder() {
        Order order = new Order();
        return order;
    }

    public List<OrderDetail> getOrderedProducts() {
        return orderedProducts;
    }

    public boolean isFinished() {
        return isFinished;
    }
    
    public void addToBasket(Product product, int quantity) {

        int summedQuantity;
        boolean quantitySmallerThanStock;
        boolean isInBasket;

        summedQuantity = checkSummedQuantity(product, quantity);
        quantitySmallerThanStock = checkIfDesiredQuantitySmallerThanStock(product, summedQuantity);

        if (!quantitySmallerThanStock) {
            return;
        }

        if (orderedProducts.isEmpty()) {
            orderedProducts.add(new OrderDetail(product, quantity));
        } else {
            isInBasket = updateIfAlreadyInBasket(product, quantity);
            if (isInBasket) {
                return;
            }
            orderedProducts.add(new OrderDetail(product, quantity));
        }
    }

    private int checkSummedQuantity(Product product, int quantity) {

        for (OrderDetail detail : orderedProducts) {
            if (detail.getProduct().equals(product)) {
                 return detail.getQuantity() + quantity;
            }
        }
        return 0;
    }

    private boolean updateIfAlreadyInBasket(Product product, int quantity) {
        for (OrderDetail detail : orderedProducts) {
            if (detail.getProduct().equals(product)) {
                detail.setQuantity(detail.getQuantity() + quantity);
                return true;
            }
        }
        return false;
    }

    public boolean checkIfDesiredQuantitySmallerThanStock(Product product, int quantity) {
        int index = Bookstore.products.indexOf(product);
        if (Bookstore.products.get(index).getStock() >= quantity) {
            return true;
        }
        return false;
    }


}
