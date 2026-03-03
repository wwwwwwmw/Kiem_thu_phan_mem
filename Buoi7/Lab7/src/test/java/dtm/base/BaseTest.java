package dtm.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public abstract class BaseTest {
    // Khai báo ThreadLocal<WebDriver> để hỗ trợ parallel execution an toàn
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(Method method) {
        // Khởi tạo ChromeDriver qua WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());

        // Maximize window, set implicit wait 10s
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Ghi log tên test đang chạy
        System.out.println("[START] Đang chạy test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Nếu result là FAILURE -> chụp screenshot lưu vào /screenshots/
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) getDriver();
                File source = ts.getScreenshotAs(OutputType.FILE);

                // Tạo thư mục nếu chưa tồn tại
                Path screenshotDir = Paths.get("screenshots");
                if (!Files.exists(screenshotDir)) {
                    Files.createDirectories(screenshotDir);
                }

                // Lưu file với tên là tên của test method
                File target = new File("screenshots/" + result.getName() + ".png");
                Files.copy(source.toPath(), target.toPath());
                System.out.println("[FAIL] Đã chụp màn hình lỗi: " + target.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Lỗi khi chụp màn hình: " + e.getMessage());
            }
        }

        // Đóng driver, remove ThreadLocal để tránh rò rỉ bộ nhớ
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public WebDriver getDriver() {
        // Trả về driver của thread hiện tại
        return driver.get();
    }
}