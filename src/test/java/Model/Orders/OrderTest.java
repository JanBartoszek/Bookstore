package Model.Orders;

import Controller.Bookstore;
import Model.Products.Book;
import Model.Products.Movie;
import Model.Products.Product;
import Model.Users.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void addToBasket() {

        Bookstore bookstore = new Bookstore();

        Product book1 = new Book("Gwiezdne Wojny", 10, 10, 100);
        Product movie1 = new Movie("Gwiezdne Wojny", 15, 10, 180);

        Bookstore.products.add(book1);
        Bookstore.products.add(movie1);


        User user = new User("DANIELOS", false);
        user.createNewOrder();

//        user.getCurrentOrder().checkIfDesiredQuantityInStock(book1, 10);

        user.addToCurrentOrder(book1, 5);

        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getProduct(), book1);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 5);

        user.addToCurrentOrder(book1, 5);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 10);

        user.addToCurrentOrder(movie1, 5);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(1).getProduct(), movie1);

        user.addToCurrentOrder(book1, 500);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 10);

    }
}