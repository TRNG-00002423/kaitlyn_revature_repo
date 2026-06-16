package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackDemo {
    // 1. Get a Logger
    private static final Logger logger = LoggerFactory.getLogger(LogBackDemo.class);

    public static void main(String[] args) {
        logger.info("Application started.");
        try {
            int result = 100 / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            logger.error("An arithmetic error occurred.");
        } finally {
            logger.info("Exception handled.");
        }
        logger.trace("Extremely detailed info - lowest severity.");
        logger.debug("Debugging details - low severity.");
        logger.info("Application exited - medium severity.");
        logger.warn("Warning messages - high severity.");
        logger.error("Error messages - high severity.");
    }
}