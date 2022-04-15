import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

class MainTest {

    @org.junit.jupiter.api.Test
    void getObjectStrings1() {
        // given
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;";
        List<String> expected = new ArrayList<>();
        expected.add("naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##");
        expected.add("naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##");
        // when
        List<String> actual = Main.getObjectStrings(input);
        // then
        Assert.assertTrue(actual.size() == 2);
        Assert.assertEquals(expected.get(0), actual.get(0));
        Assert.assertEquals(expected.get(1), actual.get(1));
    }

    @org.junit.jupiter.api.Test
    void getObjectStrings2() {
        // given
        String input = "naMe:Meredith#Brown:Libra:UnIcOrNs##naME:##NAMe:BrEAD;   price:1.23;##";
        List<String> expected = new ArrayList<>();
        expected.add("naMe:Meredith#Brown:Libra:UnIcOrNs##");
        expected.add("naME:##");
        expected.add("NAMe:BrEAD;   price:1.23;##");
        // when
        List<String> actual = Main.getObjectStrings(input);
        // then
        Assert.assertTrue(actual.size() == 3);
        Assert.assertEquals(expected.get(0), actual.get(0));
        Assert.assertEquals(expected.get(1), actual.get(1));
        Assert.assertEquals(expected.get(2), actual.get(2));
    }
}
