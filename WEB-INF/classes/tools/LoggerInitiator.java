package tools;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerInitiator {
    private static Logger logger;
    private static PropertiesSingleton config;

    private LoggerInitiator() {
    }

    private static void init() {
        config = PropertiesSingleton.getInstance();
        logger = Logger.getLogger("StrutsShop");
        logger.setLevel(Level.parse(config.getProperty("log.level")));
        try {
            FileHandler handler = new FileHandler(config.getProperty("log.file"),
                    Integer.parseInt(config.getProperty("log.size")), 1);
            handler.setFormatter(new LoggerHtmlFormatter());
            logger.addHandler(handler);
        } catch (Exception ex) {
            System.err.println("Error:" + ex);
            logger = null;
        }
    }

    public static Logger getLogger() {
        if (logger == null) {
            init();
        }
        return logger;
    }
}