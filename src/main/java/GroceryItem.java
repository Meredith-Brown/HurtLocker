import java.time.LocalDate;

public class GroceryItem {
    String name;
    String price;
    String type;
    String expiration;

    public GroceryItem(String name, String price, String type, String expiration) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public String subStringMethod(String input, int start, int end) {
        //Character[] arrayOfCharacters = new Character[](input);
        for (int i = 0; i < start; i++) {

        }
        // NO STRING UTILS
        return null;
    }

    // NAMe:BrEAD;
    // price:1.23;
    // type:Food;
    // expiration:1/25/2016##
    // [naMe:;, price:3.23;, expiration:1/04/2016##]

}
