package app.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerWrapper {
    private static LoggerWrapper instance;
    private static Logger logger;

    private LoggerWrapper() {
        logger = LoggerFactory.getLogger(LoggerWrapper.class);
    }

    public static LoggerWrapper getInstance() {
        if (instance == null) {
            instance = new LoggerWrapper();
        }
        return instance;
    }

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }
}
