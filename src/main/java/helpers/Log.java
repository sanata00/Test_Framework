package helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static Logger Log = LogManager.getLogger(Log.class.getName());

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }
    public static void info (String message, Object... obj) {
        Log.info(message, obj);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }
    public static void error (String message, Object... obj) {
        Log.error(message, obj);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
    public static void debug(String message, Object... obj) {
        Log.debug(message, obj);
    }
}
