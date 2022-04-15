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

        public void printGroceryList () { // TODO - account for 0 in Cookie
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
                                priceFrequency.put(g.price, 1);
                            }
                            groceryListCopy.get(i).name = "VOID";
                            i--;
                        }
                    }
                    System.out.printf("Item:" + "%10s %n", patternString + "%10s %n", itemCount);
                }
                String[] keys = priceFrequency.keySet().toArray(new String[0]);
                for (String price : keys) {
                    System.out.println(price + ", " + priceFrequency.get(price));
                }

                // print
                // remove item from groceryList -- mark index?
                //             System.out.println("Bread           Qty. " + breadCount);
                //            System.out.println("================             ================");
                //            String[] breadKeys = breadMap.keySet().toArray(new String[0]);
                //            for (String str : breadKeys) {
                //                System.out.println(str + "        Qty. " + breadMap.get(str));
                //                System.out.println("---------------");
                //            }
            }
        }
}
