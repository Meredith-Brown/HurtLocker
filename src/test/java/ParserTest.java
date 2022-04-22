import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser parser = new Parser();

    @Test
    void refineToList() {
        // given
        String rawData = "#naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##";
        String regexString = "name.*?[;|%|*|!|@|^]";
        List<String> expected = new ArrayList<>();
        expected.add("naMe:apPles;");
        // when
        List<String> actual = Parser.refineToList(rawData, regexString);
        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    void compileObjectData() {
        // given
        String input = "#naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##";
        List<String> expectedObjectData = new ArrayList<>();
        expectedObjectData.add("apPles");
        expectedObjectData.add("0.25");
        expectedObjectData.add("Food");
        expectedObjectData.add("1/23/2016");
        // when
        List<String> actualObjectData = parser.compileObjectData(input);
        // then
        Assert.assertEquals(expectedObjectData, actualObjectData);
    }

    @Test
    void parseValue() {
        // given
        String input = "#naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##";
        String expectedObjectString = "0.25";
        // when
        String actualObjectString = parser.parseValue(input, 3, 4);
        // then
        Assert.assertEquals(expectedObjectString, actualObjectString);
    }

    @Test
    void refineToString() {
        // given
        String inputString = "#naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##";
        String regexString = "name.*?[;|%|*|!|@|^]";
        String expectedString = "naMe:apPles;";
        // when
        String actualRefinedString = Parser.refineToString(inputString, regexString);
        // then
        Assert.assertEquals(expectedString, actualRefinedString);
    }
}