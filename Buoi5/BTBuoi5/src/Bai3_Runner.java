import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Bai3_Runner {
    public static void main(String[] args) {
        System.out.println("----------- BẮT ĐẦU CHẠY TEST BÀI 3 -----------");


        Result result = JUnitCore.runClasses(Bai3.class);


        for (Failure failure : result.getFailures()) {
            System.out.println("LỖI: " + failure.toString());
        }


        System.out.println("----------------------------------------------");
        System.out.println("Kết quả cuối cùng: " + (result.wasSuccessful() ? "THÀNH CÔNG (SUCCESS)" : "THẤT BẠI (FAIL)"));
    }
}