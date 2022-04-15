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
        String input = (new Main()).readRawDataToString();
        List<String> objectCreation = getObjectStrings(input);
        main.createGroceryItems(objectCreation);
        String listToPrint = main.printGroceryList(main.groceryList);
        System.out.println(listToPrint);
    }

    public static List<String> getObjectStrings (String rawData) {
        List<String> objectStrings = new ArrayList<>();
        Pattern patternObject = Pattern.compile("name.*?##", Pattern.CASE_INSENSITIVE);
        Matcher matcherObject = patternObject.matcher(rawData);
        while (matcherObject.find()) {
            objectStrings.add(matcherObject.group(0));
        }
        return objectStrings;
    }

    public void createGroceryItems(List<String> objectCreation) {
        for (String s : objectCreation) {
            List<String> objectData = new ArrayList<>();
            Pattern patternName = Pattern.compile("name.*?[;|%|*|!|@|^]", Pattern.CASE_INSENSITIVE);
            Matcher matcherName = patternName.matcher(s);
            List<String> intermediateN = new ArrayList<>();
            while (matcherName.find()) {
                intermediateN.add(matcherName.group(0));
            }
            for (String s2 : intermediateN) {
                Pattern patternName2 = Pattern.compile(":.*?[;|%|*|!|@|^]");
                Matcher matcherName2 = patternName2.matcher(s2);
                List<String> intermediateN2 = new ArrayList<>();
                while (matcherName2.find()) {
                    intermediateN2.add(matcherName2.group(0));
                }
                for (String s3 : intermediateN2) {
                    Pattern patternName3 = Pattern.compile("[a-zA-Z0-9]+");
                    Matcher matcherName3 = patternName3.matcher(s3);
                    while (matcherName3.find()) {
                        objectData.add(matcherName3.group(0));
                    }
                }
            }
            Pattern patternPrice = Pattern.compile("price.*?[;|%|*|!|@|^]", Pattern.CASE_INSENSITIVE);
            Matcher matcherPrice = patternPrice.matcher(s);
            List<String> intermediateP = new ArrayList<>();
            while (matcherPrice.find()) {
                intermediateP.add(matcherPrice.group(0));
            }
            for (String s4 : intermediateP) {
                Pattern patternPrice2 = Pattern.compile("[0-9]*\\.?[0-9][0-9]");
                Matcher matcherPrice2 = patternPrice2.matcher(s4);
                while (matcherPrice2.find()) {
                    objectData.add(matcherPrice2.group(0));
                }
            }
            Pattern patternType = Pattern.compile("type.*?[;|%|*|!|@|^]", Pattern.CASE_INSENSITIVE);
            Matcher matcherType = patternType.matcher(s);
            List<String> intermediateT = new ArrayList<>();
            while (matcherType.find()) {
                intermediateT.add(matcherType.group(0));
            }
            for (String s5 : intermediateT) {
                Pattern patternType2 = Pattern.compile(":.*?[;|%|*|!|@|^]");
                Matcher matcherType2 = patternType2.matcher(s5);
                List<String> intermediateT2 = new ArrayList<>();
                while (matcherType2.find()) {
                    intermediateT2.add(matcherType2.group(0));
                }
                for (String s6 : intermediateT2) {
                    Pattern patternType3 = Pattern.compile("[a-zA-Z0-9]+");
                    Matcher matcherType3 = patternType3.matcher(s6);
                    while (matcherType3.find()) {
                        objectData.add(matcherType3.group(0));
                    }
                }
            }
            Pattern patternEDate = Pattern.compile("[0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
            Matcher matcherEDate = patternEDate.matcher(s);
            while (matcherEDate.find()) {
                objectData.add(matcherEDate.group(0));
            }
            if (objectData.size() == 4) {
                GroceryItem groceryItem = new GroceryItem(objectData.get(0), objectData.get(1),
                        objectData.get(2), objectData.get(3));
                groceryList.add(groceryItem);
            } else {
                errorsCount++;
            }
        }
    }

    public String printGroceryList(List<GroceryItem> groceryList) {
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
