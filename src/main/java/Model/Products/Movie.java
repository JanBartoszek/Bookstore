package Model.Products;

public class Movie extends Product {

    private int length;

    public Movie(String title, int stock, int value, int length) {
        super(title, stock, value);
        this.length = length;
    }

    public int getSpecialValue() {
        return length;
    }
}
