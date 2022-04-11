import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        // pattern matcher ignore casing
        // matcher.find --- use regex to indicate anything goes and then a ;
        // create object
        String input = (new Main()).readRawDataToString();
        List<String> objectCreation = new ArrayList<>();
        Pattern patternName = Pattern.compile("name", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternName.matcher(input);
        while (matcher.find()) {
            objectCreation.add("x");
        }
        System.out.println(objectCreation.get(0));
        System.out.println(objectCreation.get(1));
            // matcher has anchoring bounds


    }

}

//     public String changeHoratioToTariq(String hamletText) {
//        if (findHoratio(hamletText) > 0) {
//            Pattern pattern = Pattern.compile("Horatio");
//            Matcher matcher = pattern.matcher(hamletText);
//            String updatedHamletText0 = matcher.replaceAll("Tariq");
//            Pattern pattern2 = Pattern.compile("HORATIO");
//            Matcher matcher2 = pattern2.matcher(updatedHamletText0);
//            String updatedHamletText = matcher2.replaceAll("TARIQ");
//            return updatedHamletText;
//        }
//        return null;
//    }
//
//    public int findHamlet(String hamletText) { // returns count
//        int count = 0;
//        Pattern pattern = Pattern.compile("Hamlet");
//        Matcher matcher = pattern.matcher(hamletText);
//        while (matcher.find()) {
//            count++;
//        }
//        Pattern pattern2 = Pattern.compile("HAMLET");
//        Matcher matcher2 = pattern2.matcher(hamletText);
//        while (matcher2.find()) {
//            count++;
//        }
//        return count;
//    }
