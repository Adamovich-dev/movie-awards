package com.adamovichdev.movieawards.Util;
import org.slf4j.Logger;

import java.util.Arrays;

public final class LoggerUtil {

    public static void logRequest(Logger logger, String methodName, Object ...requestParams) {
        logger.debug(String.format("Call method %s with params %s", methodName, Arrays.toString(requestParams)));
    }

    public static void logResponse(Logger logger, String methodName, Object response) {
        logger.debug(String.format("Call method %s, response %s", methodName, response));
    }

    public static void logException(Logger logger, String methodName, Exception e) {
        logger.error(String.format("Method %s throw %s\n cause: %s\n message: %s", methodName, e.getClass().getName(), e.getCause(), e.getMessage()));
    }
}
