package com.amazon.e2e.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Loads config/{env}.properties where env comes from -Denv (dev | qa | prod, default dev). */
public final class ConfigReader {
    private static final String ENV = System.getProperty("env", "dev").toLowerCase();
    private static final Properties PROPS = load();

    private ConfigReader() {}

    private static Properties load() {
        String file = "config/" + ENV + ".properties";
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream(file)) {
            if (in == null) {
                throw new IllegalStateException("Unknown env '" + ENV + "': missing " + file);
            }
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + file, e);
        }
    }

    public static String env() { return ENV; }

    public static String baseUrl() { return PROPS.getProperty("base.url"); }

    public static String browser() { return PROPS.getProperty("browser", "chromium"); }

    /** -Dheadless=true|false overrides the value in the env file. */
    public static boolean headless() {
        String override = System.getProperty("headless");
        return Boolean.parseBoolean(override != null && !override.isBlank()
                && !override.startsWith("${") ? override : PROPS.getProperty("headless", "true"));
    }

    public static int slowMoMs() { return Integer.parseInt(PROPS.getProperty("slow.mo.ms", "0")); }

    public static int timeoutMs() { return Integer.parseInt(PROPS.getProperty("timeout.ms", "30000")); }
}
