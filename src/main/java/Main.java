import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main { // TODO - write tests!!!
    public List<GroceryItem> groceryList = new ArrayList<>();
    public int errorsCount = 0;

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        Main main = new Main();
        Parser parser = new Parser();
        String input = main.readRawDataToString();
        List<String> allObjects = parser.refineToList(input, "name.*?##");
        for (String objectString : allObjects) {
            List<String> objectData = parser.compileObjectData(objectString);
            main.createGroceryItem(objectData);
        }
        main.printGroceryList();
    }

    public void createGroceryItem (List<String> objectData) {
        objectData.remove(null);
        try {
            GroceryItem groceryItem = new GroceryItem(objectData.get(0), objectData.get(1),
                    objectData.get(2), objectData.get(3));
            groceryList.add(groceryItem);
        } catch (Exception e) {
            e.printStackTrace();
            errorsCount++;
        }
    }

        public void printGroceryList () {
            List<GroceryItem> groceryListCopy = groceryList;
            for (GroceryItem g : groceryListCopy) {
                Map<String, Integer> priceFrequency = new HashMap<>();
                if (g.name != "VOID") {
                    String patternString = g.name;
                    int itemCount = 0;
                    for (int i = 0; i < groceryListCopy.size(); i++) {
                        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(groceryListCopy.get(i).name);
                        while (matcher.find()) {
                            itemCount++;
                            if (priceFrequency.containsKey(groceryListCopy.get(i).price)) {
                                int current = priceFrequency.get(groceryListCopy.get(i).price);
                                priceFrequency.replace(groceryListCopy.get(i).price, current + 1);
                            } else {
                                priceFrequency.put(groceryListCopy.get(i).price, 1);
                            }
                            groceryListCopy.get(i).name = "VOID";
                            i--;
                        }
                    }
                    System.out.printf("%-8s %8s %8s %-8s %8s %n", "Item:", patternString, " ", "Qty:",
                            itemCount);
                    System.out.println("=================          =================");
                    String[] keys = priceFrequency.keySet().toArray(new String[0]);
                    for (String price : keys) {
                        System.out.printf("%-8s %8s %8s %-8s %8s %n", "Price:", price, " ", "Qty:",
                                priceFrequency.get(price));
                        System.out.println("-----------------          -----------------");
                    }
                    System.out.println(" ");
                }
            }
            System.out.printf("%-8s %8s %8s %-8s %8s %n", "Errors", " ", " ", "Qty:", errorsCount);
        }
}
