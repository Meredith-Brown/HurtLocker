import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroceryItemTest {

    @Test
    void constructor() {
        // given
        String inputName = "wAffLES";
        String expectedName = "Waffles";
        String expectedPrice = "$6.75";
        String expectedType = "food";
        String expectedExpiration = "06/25/22";
        // when
        GroceryItem groceryItem = new GroceryItem(inputName, expectedPrice, expectedType, expectedExpiration);
        // then
        Assert.assertEquals(expectedName, groceryItem.name);
        Assert.assertEquals(expectedPrice, groceryItem.price);
        Assert.assertEquals(expectedType, groceryItem.type);
        Assert.assertEquals(expectedExpiration, groceryItem.expiration);
    }

    @Test
    void zeroToO() {
        // given
        String inputName = "B0l0gna";
        String expectedName = "Bologna";
        GroceryItem groceryItem = new GroceryItem("test", "test", "test", "test");
        // when
        String actualName = groceryItem.zeroToO(inputName);
        // then
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    void caseCorrection() {
        // given
        String inputName = "lAsAgNA";
        String expectedName = "Lasagna";
        GroceryItem groceryItem = new GroceryItem("test", "test", "test", "test");
        // when
        String actualName = groceryItem.caseCorrection(inputName);
        // then
        Assert.assertEquals(expectedName, actualName);
    }
}