import org.junit.Assert;
import org.junit.Test;

public class Bai2_Test2 {

    @Test
    public void createAndSetName() {
        System.out.println("--> Chạy Test 2: So sánh chuỗi");
        String expected = "Y";
        String actual = "Y";


        Assert.assertEquals(expected, actual);
        System.out.println("Suite Test 2 is successful " + actual);
    }
}