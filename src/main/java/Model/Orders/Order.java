package Model.Orders;

import Controller.Bookstore;
import Model.Products.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderDetail> orderedProducts = new ArrayList<>();
    private boolean isFinished;

    public static Order createNewOrder() {
        return new Order();
    }

    public List<OrderDetail> getOrderedProducts() {
        return orderedProducts;
    }

    public boolean isFinished() {
        return isFinished;
    }
    
    public void addToBasket(Product product, int quantity) {

        int summedQuantityOfSameProducts;
        boolean quantityExceedsStock;
        boolean isInBasket;

        summedQuantityOfSameProducts = checkSummedQuantity(product, quantity);
        quantityExceedsStock = checkIfDesiredQuantityExceedsStock(product, summedQuantityOfSameProducts);

        if (quantityExceedsStock) {
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

        if (orderedProducts.isEmpty()){
            return quantity;
        }

        for (OrderDetail detail : orderedProducts) {
            if (detail.getProduct().equals(product)) {
                 return detail.getQuantity() + quantity;
            }
        }
        return quantity;
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

    private boolean checkIfDesiredQuantityExceedsStock(Product product, int quantity) {
        int index = Bookstore.products.indexOf(product);
        return Bookstore.products.get(index).getStock() < quantity;
    }


    public void removeFromBasket(Product product, int quantity) {

        int qunatityAfterRemoval;
        
        for (OrderDetail detail : orderedProducts) {
            if (detail.getProduct().equals(product)) {
                qunatityAfterRemoval = detail.getQuantity() - quantity;
                if (qunatityAfterRemoval <= 0){
                    orderedProducts.remove(detail);
                    return;
                }
                detail.setQuantity(qunatityAfterRemoval);
                return;
            }
        }
    }
}
