package com.amazon.e2e.base;

import com.amazon.e2e.config.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/** Creates and owns the Playwright/Browser/Context/Page for one test. */
public class DriverFactory {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    public Page start() {
        playwright = Playwright.create();
        BrowserType type = switch (ConfigReader.browser()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };
        browser = type.launch(new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.headless())
                .setSlowMo(ConfigReader.slowMoMs()));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1440, 900));
        context.setDefaultTimeout(ConfigReader.timeoutMs());
        return context.newPage();
    }

    public void stop() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
