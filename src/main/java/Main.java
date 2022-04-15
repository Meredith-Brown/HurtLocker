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
        String input = (new Main()).readRawDataToString();
        List<String> allObjects = parser.refineToList(input, "name.*?##");
        for (String objectString : allObjects) {
            List<String> objectData = parser.compileObjectData(objectString);
            main.createGroceryItem(objectData);
        }
        String listToPrint = main.printGroceryList(main.groceryList);
        System.out.println(listToPrint);
    }

    public void createGroceryItem (List<String> objectData) {
        objectData.remove(null);
        if (objectData.size() == 4) {
            GroceryItem groceryItem = new GroceryItem(objectData.get(0), objectData.get(1),
                    objectData.get(2), objectData.get(3));
            groceryList.add(groceryItem);
        } else {
            errorsCount++;
        }
    }

        public String printGroceryList (List < GroceryItem > groceryList) {
            // MAPS: key = price, value = count @ that price
            int milkCount = 0;
            Map<String, Integer> milkMap = new HashMap<>();
            int breadCount = 0;
            Map<String, Integer> breadMap = new HashMap<>();
            int cookiesCount = 0; // TODO - account for 0 in Cookie
            Map<String, Integer> cookiesMap = new HashMap<>();
            int applesCount = 0;
            Map<String, Integer> applesMap = new HashMap<>();
            for (GroceryItem g : groceryList) {
                Pattern patternMilk = Pattern.compile("milk", Pattern.CASE_INSENSITIVE);
                Matcher matcherMilk = patternMilk.matcher(g.name);
                while (matcherMilk.find()) {
                    milkCount++;
                    if (milkMap.containsKey(g.price)) {
                        int current = milkMap.get(g.price);
                        milkMap.replace(g.price, current + 1);
                    } else {
                        milkMap.put(g.price, 1);
                    }
                }
                Pattern patternBread = Pattern.compile("bread", Pattern.CASE_INSENSITIVE);
                Matcher matcherBread = patternBread.matcher(g.name);
                while (matcherBread.find()) {
                    breadCount++;
                    if (breadMap.containsKey(g.price)) {
                        int current = breadMap.get(g.price);
                        breadMap.replace(g.price, current + 1);
                    } else {
                        breadMap.put(g.price, 1);
                    }
                }
                Pattern patternCookie = Pattern.compile("cookie", Pattern.CASE_INSENSITIVE);
                Matcher matcherCookie = patternCookie.matcher(g.name);
                while (matcherCookie.find()) {
                    cookiesCount++;
                    if (cookiesMap.containsKey(g.price)) {
                        int current = cookiesMap.get(g.price);
                        cookiesMap.replace(g.price, current + 1);
                    } else {
                        cookiesMap.put(g.price, 1);
                    }
                }
                Pattern patternApples = Pattern.compile("apples", Pattern.CASE_INSENSITIVE);
                Matcher matcherApples = patternApples.matcher(g.name);
                while (matcherApples.find()) {
                    applesCount++;
                    if (applesMap.containsKey(g.price)) {
                        int current = applesMap.get(g.price);
                        applesMap.replace(g.price, current + 1);
                    } else {
                        applesMap.put(g.price, 1);
                    }
                }
            }
            // TODO - add actual formatting
            System.out.println("Milk           Qty. " + milkCount);
            System.out.println("================             ================");
            String[] milkKeys = milkMap.keySet().toArray(new String[0]);
            for (String str : milkKeys) {
                System.out.println(str + "        Qty. " + milkMap.get(str));
                System.out.println("---------------");
            }
            System.out.println("Bread           Qty. " + breadCount);
            System.out.println("================             ================");
            String[] breadKeys = breadMap.keySet().toArray(new String[0]);
            for (String str : breadKeys) {
                System.out.println(str + "        Qty. " + breadMap.get(str));
                System.out.println("---------------");
            }
            System.out.println("Cookies           Qty. " + cookiesCount);
            System.out.println("================             ================");
            String[] cookiesKeys = cookiesMap.keySet().toArray(new String[0]);
            for (String str : cookiesKeys) {
                System.out.println(str + "        Qty. " + cookiesMap.get(str));
                System.out.println("---------------");
            }
            System.out.println("Apples           Qty. " + applesCount);
            System.out.println("================             ================");
            String[] applesKeys = applesMap.keySet().toArray(new String[0]);
            for (String str : applesKeys) {
                System.out.println(str + "        Qty. " + applesMap.get(str));
                System.out.println("---------------");
            }


            String output = ("Milk           Qty. " + milkCount + "\n" + "Bread          Qty. " + breadCount
                    + "\n" + "Cookies        Qty. " + cookiesCount + "\n" + "Apples         Qty. " +
                    applesCount + "\n");
            return output;
        }
}
