package logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    private static final Log INSTANCE = new Log();
    private static Logger logger = LoggerFactory.getLogger(Log.class);

    private Log(){}
    public Log getInstance(){
        return INSTANCE;
    }
    public static void info(String message){
        logger.info(message);
    }
}
