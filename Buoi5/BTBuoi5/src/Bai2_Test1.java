import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Bai2_Test1 {
    public String message = "Fpoly";


    Bai2 junitMessage = new Bai2(message);

    @Test
    public void testJUnitMessage() {
        System.out.println("--> Chạy Test 1: In tin nhắn");
        junitMessage.printMessage();
    }

    @Test
    public void testJUnitHiMessage() {
        message = "Hi!" + message;
        System.out.println("--> Chạy Test 1: In tin nhắn có chữ Hi!");
        assertEquals(message, junitMessage.printHiMessage());
    }
}