package Model.Products;

public abstract class Product {

    protected String title;
    protected int stock;
    protected int value;

    public Product(String title, int stock, int value) {
        this.title = title;
        this.stock = stock;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public abstract int getSpecialValue();
}