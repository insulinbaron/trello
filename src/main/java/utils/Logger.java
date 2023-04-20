package utils;

import org.apache.logging.log4j.Level;

public class Logger {
    private static final org.apache.logging.log4j.Logger logger = null;

    public static void log(String message){
        logger.log(Level.INFO, message);
    }
}
