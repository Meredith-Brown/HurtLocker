import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public List<GroceryItem> groceryList = new ArrayList<>();

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
        List<String> objectData = new ArrayList<>();
        for (String s : objectCreation) {
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
            while (matcherType.find()) {
                objectData.add(matcherType.group(0));
            }
            Pattern patternEDate = Pattern.compile("[0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
            Matcher matcherEDate = patternEDate.matcher(s);
            while (matcherEDate.find()) {
                objectData.add(matcherEDate.group(0));
            }
            //if (objectData.get()) // NOT NULL
            //GroceryItem groceryItem = new GroceryItem(objectData.get(0), objectData.get(1),
                    //objectData.get(2), objectData.get(3));
            //groceryList.add(groceryItem); // IF NOT NULL
        }
        for (int i = 0; i < objectData.size(); i++) {
            if (objectData.get(i) == null) {
                objectData.remove(i);
                System.out.println("REMOVE");
                i--;
            } else {
                System.out.println(objectData.get(i));
            }
        }
    }

}
