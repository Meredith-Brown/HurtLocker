import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void createGroceryItem1() {
        // given
        List<String> objectData = new ArrayList<>();
        objectData.add("name");
        objectData.add("price");
        objectData.add("type");
        objectData.add("expiration");
        int beforeCGI = Main.groceryList.size();
        // when
        Main.createGroceryItem(objectData);
        // then
        Assert.assertTrue(beforeCGI + 1 == Main.groceryList.size());
    }

    @Test
    void createGroceryItem2() {
        // given
        List<String> objectData = new ArrayList<>();
        objectData.add("name");
        objectData.add("price");
        objectData.add("type");
        int beforeCGI = Main.groceryList.size();
        int startingErrors = Main.errorsCount;
        // when
        Main.createGroceryItem(objectData);
        // then
        Assert.assertTrue(beforeCGI == Main.groceryList.size());
        Assert.assertTrue(startingErrors + 1 == Main.errorsCount);
    }
}