import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroceryItem {
    String name;
    String price;
    String type;
    String expiration;

    public GroceryItem(String name, String price, String type, String expiration) {
        String nameCorrected0O = zeroToO(name);
        String nameCaseCorrected = caseCorrection(nameCorrected0O);
        this.name = nameCaseCorrected;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public String zeroToO(String input) {
        Pattern pattern = Pattern.compile("0");
        Matcher matcher = pattern.matcher(input);
        String output = matcher.replaceAll("o");
        return output;
    }

    public String caseCorrection(String input) {
        String[] upper = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] lower = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (int i = 0; i < upper.length; i++) { // all letters to lower
            Pattern patternToL = Pattern.compile(upper[i]);
            Matcher matcherToL = patternToL.matcher(input);
            input = matcherToL.replaceAll(lower[i]);
        }
        for (int i = 0; i < lower.length; i++) { // first letter to upper
            Pattern patternToU = Pattern.compile("\\A" + lower[i]); //pattern = first letter
            Matcher matcherToU = patternToU.matcher(input);
            input = matcherToU.replaceAll(upper[i]);
        }
        return input;
    }

}
