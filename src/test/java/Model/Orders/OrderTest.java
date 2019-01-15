package Model.Orders;

import Controller.Bookstore;
import Model.Products.Book;
import Model.Products.Movie;
import Model.Products.Product;
import Model.Users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Bookstore bookstore;
    private Product book1;
    private Product movie1;
    private User user;

    @BeforeEach
    void setUp(){
        bookstore = new Bookstore();
        book1 = new Book("Gwiezdne Wojny", 10, 10, 100);
        movie1 = new Movie("Władca Pierścieni", 15, 10, 180);
        user = new User("janek", false);
    }

    @AfterEach
    void tearDown(){
        bookstore = null;
        book1 = null;
        movie1 = null;
        user = null;
        Bookstore.products.clear();
    }

    @Test
    void addOneItemToBasket() {

        Bookstore.products.add(book1);

        user.createNewOrder();
        user.addToCurrentOrder(book1, 5);

        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getProduct(), book1);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 5);
        assertEquals(user.getCurrentOrder().getOrderedProducts().size(), 1);
//
//        user.addToCurrentOrder(book1, 5);
//        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 10);
//
//        user.addToCurrentOrder(movie1, 5);
//        assertEquals(user.getCurrentOrder().getOrderedProducts().get(1).getProduct(), movie1);
//
//        user.addToCurrentOrder(book1, 500);
//        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 10);

    }

    @Test
    void addTwoSameItemsToBasket() {

        Bookstore.products.add(book1);

        user.createNewOrder();
        user.addToCurrentOrder(book1, 5);
        user.addToCurrentOrder(book1, 5);


        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getProduct(), book1);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 10);
        assertEquals(user.getCurrentOrder().getOrderedProducts().size(), 1);
    }

    @Test
    void addTwoDifferentItemsToBasket() {

        Bookstore.products.add(book1);
        Bookstore.products.add(movie1);

        user.createNewOrder();
        user.addToCurrentOrder(book1, 5);
        user.addToCurrentOrder(movie1, 5);


        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getProduct(), book1);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(0).getQuantity(), 5);

        assertEquals(user.getCurrentOrder().getOrderedProducts().get(1).getProduct(), movie1);
        assertEquals(user.getCurrentOrder().getOrderedProducts().get(1).getQuantity(), 5);

        assertEquals(user.getCurrentOrder().getOrderedProducts().size(), 2);
    }

}