package com.amazon.e2e.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Central logger factory (Log4j2; output goes to console and logs/{env}.log). */
public final class LogUtil {
    private LogUtil() {}

    public static Logger get(Class<?> cls) {
        return LogManager.getLogger(cls);
    }
}
