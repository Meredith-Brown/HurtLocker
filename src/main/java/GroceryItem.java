import java.time.LocalDate;

public class GroceryItem {
    String name;
    float price;
    String type;
    //LocalDate expiration;

    public GroceryItem(String name, float price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        //this.expiration = expiration;
    }

}
