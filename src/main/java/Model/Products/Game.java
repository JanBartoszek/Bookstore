package Model.Products;

public class Game extends Product {

    private int ageLimit;
    
    public Game(String title, int stock, int value, int ageLimit) {
        super(title, stock, value);
        this.ageLimit = ageLimit;
    }

    public int getSpecialValue() {
        return ageLimit;
    }
}
