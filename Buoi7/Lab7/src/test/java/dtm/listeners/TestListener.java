package dtm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== BẮT ĐẦU CHẠY TEST SUITE: " + context.getName() + " ===");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println(">>> Đang chạy Test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(" [PASS] " + result.getName() + " - Hoàn thành xuất sắc!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println(" [FAIL] " + result.getName() + " - LỖI RỒI! Hãy kiểm tra screenshot.");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== KẾT THÚC TEST SUITE. Tổng đạt: "
                + context.getPassedTests().size() + "/"
                + (context.getPassedTests().size() + context.getFailedTests().size()) + " ===");
    }
}