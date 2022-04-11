import java.time.LocalDate;

public class GroceryItem {
    String name;
    String price; // TODO - change to float?
    String type;
    String expiration; // TODO - change to LocalDate?

    public GroceryItem(String name, String price, String type, String expiration) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

}
