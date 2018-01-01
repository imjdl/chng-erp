package cn.com.chng.erp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by liuyandong on 2017/7/7.
 */
public class LogUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void warn(String message) {
        LOGGER.warn(message);
    }

    public static void error(String errorMessage, String classSimpleName, String methodName, String exceptionSimpleName, String exceptionMessage, Map<String, String> parameters) {
        LOGGER.error("{}:{}.{}-{}-{}-{}", errorMessage, classSimpleName, methodName, exceptionSimpleName, exceptionMessage, parameters);
    }

    public static void error(String errorMessage, String classSimpleName, String methodName, String exceptionSimpleName, String exceptionMessage) {
        LOGGER.error("{}:{}.{}-{}-{}", errorMessage, classSimpleName, methodName, exceptionSimpleName, exceptionMessage);
    }

    public static void error(String errorMessage, String classSimpleName, String methodName, Exception exception, Map<String, String> parameters) {
        LOGGER.error("{}:{}.{}-{}-{}-{}", errorMessage, classSimpleName, methodName, exception.getClass().getSimpleName(), exception.getMessage(), parameters);
    }

    public static void error(String errorMessage, String classSimpleName, String methodName, Exception exception) {
        LOGGER.error("{}:{}.{}-{}-{}", errorMessage, classSimpleName, methodName, exception.getClass().getSimpleName(), exception.getMessage());
    }

    public static void error(String errorMessage) {
        LOGGER.error(errorMessage);
    }
}
