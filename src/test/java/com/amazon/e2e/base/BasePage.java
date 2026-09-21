package com.amazon.e2e.base;

import com.amazon.e2e.config.ConfigReader;
import com.amazon.e2e.utilities.LogUtil;
import com.amazon.e2e.utilities.ScreenshotUtil;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

/** Parent of every page object: shared actions, logging and screenshots. */
public abstract class BasePage {
    protected final Page page;
    protected final Logger log = LogUtil.get(getClass());

    protected BasePage(Page page) {
        this.page = page;
    }

    protected void open(String path) {
        String url = ConfigReader.baseUrl() + path;
        log.info("Navigating to {}", url);
        page.navigate(url);
    }

    protected void click(Locator locator, String description) {
        log.info("Click: {}", description);
        locator.click();
    }

    protected void type(Locator locator, String text, String description) {
        log.info("Type '{}' into: {}", text, description);
        locator.fill(text);
    }

    public Path takeScreenshot(String name) {
        return ScreenshotUtil.capture(page, name);
    }
}
