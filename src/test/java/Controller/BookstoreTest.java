package Controller;

import Model.Orders.Order;
import Model.Products.Book;
import Model.Products.Movie;
import Model.Products.Product;
import Model.Users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreTest {

    private Bookstore bookstore;
    private Product book1;
    private Product book2;
    private Product movie1;
    private Product movie2;
    private User user1;
    private User user2;
    private Order userOrder1;
    private Order userOrder2;

    @BeforeEach
    void setUp(){
        bookstore = new Bookstore();
        book1 = new Book("Gwiezdne Wojny", 10, 10, 100);
        movie1 = new Movie("Władca Pierścieni", 15, 10, 180);
        book2 = new Book("Lalka", 100, 10, 100);
        movie2 = new Movie("Titanic", 150, 10, 180);

        Bookstore.products.add(book1);
        Bookstore.products.add(movie1);
        Bookstore.products.add(book2);
        Bookstore.products.add(movie2);

        user1 = new User("janek", false, bookstore);
        user1.createNewOrder();
        userOrder1 = user1.getCurrentOrder();
        user1.addToCurrentOrder(book1, 5);
        user1.addToCurrentOrder(movie1, 5);

        user2 = new User("daniel", true, bookstore);
        user2.createNewOrder();
        userOrder2 = user2.getCurrentOrder();
        user2.addToCurrentOrder(book1, 5);
        user2.addToCurrentOrder(movie1, 5);
        user2.addToCurrentOrder(book2, 50);
        user2.addToCurrentOrder(movie2, 50);
    }

    @AfterEach
    void tearDown(){
        bookstore = null;
        book1 = null;
        movie1 = null;
        book2 = null;
        movie2 = null;
        user1 = null;
        userOrder1 = null;
        user2 = null;
        userOrder2 = null;
        Bookstore.products.clear();
    }

    @Test
    void basicCase() {

        bookstore.handleUserOrder(userOrder1);

        assertEquals(Bookstore.products.get(0).getStock(), 5);
        assertEquals(Bookstore.products.get(1).getStock(), 10);
    }

    @Test
    void twoUsers(){

        bookstore.handleUserOrder(userOrder1);
        bookstore.handleUserOrder(userOrder2);

        assertEquals(Bookstore.products.get(0).getStock(), 0);
        assertEquals(Bookstore.products.get(1).getStock(), 5);
        assertEquals(Bookstore.products.get(2).getStock(), 50);
        assertEquals(Bookstore.products.get(3).getStock(), 100);
    }
}