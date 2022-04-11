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
        for (String s : objectCreation) {
            List<String> objectData = new ArrayList<>();
            Pattern patternName = Pattern.compile("name.*?[;|%|*|!|@]", Pattern.CASE_INSENSITIVE);
            Matcher matcherName = patternName.matcher(s);
            while (matcherName.find()) {
                objectData.add(matcherName.group(0));
            }
            Pattern patternPrice = Pattern.compile("price.*?[;|%|*|!|@]", Pattern.CASE_INSENSITIVE);
            Matcher matcherPrice = patternPrice.matcher(s);
            while (matcherPrice.find()) {
                objectData.add(matcherPrice.group(0));
            }
            Pattern patternType = Pattern.compile("type.*?[;|%|*|!|@]", Pattern.CASE_INSENSITIVE);
            Matcher matcherType = patternType.matcher(s);
            while (matcherType.find()) {
                objectData.add(matcherType.group(0));
            }
            Pattern patternEDate = Pattern.compile("expiration.*?##", Pattern.CASE_INSENSITIVE);
            Matcher matcherEDate = patternEDate.matcher(s);
            while (matcherEDate.find()) {
                objectData.add(matcherEDate.group(0));
            }
            //GroceryItem groceryItem = new GroceryItem(objectData.get(0), objectData.get(1),
                    //objectData.get(2), objectData.get(3));
            //groceryList.add(groceryItem);
            System.out.println(objectData);
        }
    }

}
