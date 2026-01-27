import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Bai1_Runner {
    public static void main(String[] args) {
        System.out.println("----------- BẮT ĐẦU CHẠY TEST BÀI 1 -----------");

        Result result = JUnitCore.runClasses(Bai1Test.class);


        System.out.println("Run tests: " + result.getRunCount());
        System.out.println("Failed tests: " + result.getFailureCount());
        System.out.println("Ignored tests: " + result.getIgnoreCount());
        System.out.println("Success: " + result.wasSuccessful());


        for (Failure failure : result.getFailures()) {
            System.out.println("Chi tiết lỗi: " + failure.toString());
        }
    }
}