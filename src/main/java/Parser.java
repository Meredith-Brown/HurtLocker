import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public String[] regex = {"name.*?[;|%|*|!|@|^]", ":.*?[;|%|*|!|@|^]", "[a-zA-Z0-9]+",
            "price.*?[;|%|*|!|@|^]", "[0-9]*\\.?[0-9][0-9]", "type.*?[;|%|*|!|@|^]",
            ":.*?[;|%|*|!|@|^]", "[a-zA-Z0-9]+", "[0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]"};

    public static List<String> refineToList (String rawData, String regexString) {
        List<String> objectStrings = new ArrayList<>();
        Pattern patternObject = Pattern.compile(regexString, Pattern.CASE_INSENSITIVE);
        Matcher matcherObject = patternObject.matcher(rawData);
        while (matcherObject.find()) {
            objectStrings.add(matcherObject.group(0));
        }
        return objectStrings;
    }

    public List<String> compileObjectData(String objectString) {
        List<String> objectData = new ArrayList<>();
        objectData.add(parseValue(objectString, 0, 2));
        objectData.add(parseValue(objectString, 3, 4));
        objectData.add(parseValue(objectString, 5, 7));
        objectData.add(parseValue(objectString, 8, 8));
        return objectData;
    }

    public String parseValue(String objectString, int start, int end) {
        for (int i = start; i <= end; i++) {
            objectString = refineToString(objectString, regex[i]);
        }
        return objectString;
    }

    public static String refineToString (String rawData, String regexString) {
        Pattern patternObject = Pattern.compile(regexString, Pattern.CASE_INSENSITIVE);
        Matcher matcherObject = patternObject.matcher(rawData);
        while (matcherObject.find()) {
            return matcherObject.group(0);
        }
        return null;
    }

}
