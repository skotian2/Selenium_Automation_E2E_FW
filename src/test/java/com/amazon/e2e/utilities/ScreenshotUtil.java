package com.amazon.e2e.utilities;

import com.amazon.e2e.config.ConfigReader;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Saves timestamped screenshots to screenshots/{env}/. */
public final class ScreenshotUtil {
    private static final Logger LOG = LogUtil.get(ScreenshotUtil.class);
    private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtil() {}

    public static Path capture(Page page, String name) {
        try {
            Path dir = Paths.get("screenshots", ConfigReader.env());
            Files.createDirectories(dir);
            String safe = name.replaceAll("[^a-zA-Z0-9_-]", "_");
            Path file = dir.resolve(safe + "_" + LocalDateTime.now().format(STAMP) + ".png");
            page.screenshot(new Page.ScreenshotOptions().setPath(file));
            LOG.info("Screenshot saved: {}", file.toAbsolutePath());
            return file;
        } catch (IOException e) {
            throw new IllegalStateException("Could not save screenshot " + name, e);
        }
    }
}
