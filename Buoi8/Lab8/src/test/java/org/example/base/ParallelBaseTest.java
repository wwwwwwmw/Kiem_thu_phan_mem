package org.example.base;

import org.example.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class ParallelBaseTest {

    protected String baseUrl;

    @Parameters({"baseUrl", "browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("https://www.saucedemo.com/") String baseUrl,
                      @Optional("chrome") String browser) {
        this.baseUrl = baseUrl;
        DriverFactory.initDriver(browser);
        getDriver().get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(15));
    }
}