package Model.Products;

public class Book extends Product {

    private int numOfPages;

    public Book(String title, int stock, int value, int numOfPages) {
        super(title, stock, value);
        this.numOfPages = numOfPages;
    }

    public int getSpecialValue() {
        return numOfPages;
    }
}
