package com.amazon.e2e.base;

import com.amazon.e2e.config.ConfigReader;
import com.amazon.e2e.utilities.LogUtil;
import com.amazon.e2e.utilities.ScreenshotUtil;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/** Parent of every test: browser lifecycle, failure screenshots, start/end logging. */
public abstract class BaseTest {
    protected final Logger log = LogUtil.get(getClass());
    private DriverFactory driver;
    protected Page page;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        log.info("START [{}] {}", ConfigReader.env(), method.getName());
        driver = new DriverFactory();
        page = driver.start();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && page != null) {
            ScreenshotUtil.capture(page, "FAILED_" + result.getName());
        }
        log.info("END {} -> {}", result.getName(), result.isSuccess() ? "PASS" : "FAIL");
        driver.stop();
    }
}
