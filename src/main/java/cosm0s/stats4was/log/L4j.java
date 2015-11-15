package cosm0s.stats4was.log;

import cosm0s.stats4was.utils.DaemonContext;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * L4j log for java controler
 * This class configure apache log4j and implement default methos for debugging, warning, etc...
 */
public class L4j {

    private static final L4j instance;
    static Logger log;

    static {
        instance = new L4j();
    }

    private L4j(){
        ConsoleAppender console = new ConsoleAppender();
        console.setName(DaemonContext.instance().getProperty("appName"));
        console.setLayout(new PatternLayout(DaemonContext.instance().getProperty("l4jPattern")));
        console.setThreshold(Level.toLevel(DaemonContext.instance().getProperty("l4jLevel")));
        console.activateOptions();
        Logger.getRootLogger().addAppender(console);
        log = Logger.getLogger(DaemonContext.instance().getProperty("l4jgetLogger"));

    }

    public static L4j getL4j() {
        return instance;
    }

    /**
     * If the system propertie loger-config-path exist use this method for configuration load
     * @param filename path loger config
     * @param level log4j level: debug, info, warning, etc...
     */
    public void setConfig(String filename, String level) {
        FileAppender fileAppender = new FileAppender();
        fileAppender.setName(DaemonContext.instance().getProperty("appName"));
        fileAppender.setFile(filename);
        fileAppender.setLayout(new PatternLayout(DaemonContext.instance().getProperty("l4jPatternConfig")));
        fileAppender.setThreshold(Level.toLevel(level));
        fileAppender.setAppend(true);
        fileAppender.activateOptions();
        Logger.getRootLogger().getLoggerRepository().resetConfiguration();
        Logger.getRootLogger().addAppender(fileAppender);
        log = Logger.getLogger(DaemonContext.instance().getProperty("appName"));
    }

    public void critical(String msg) {
        if (log.isEnabledFor(Level.FATAL)) {
            log.fatal(msg);
        }
    }

    public void error(String msg) {
        if (log.isEnabledFor(Level.ERROR)) {
            log.error(msg);
        }
    }

    public void error(String msg, Throwable t) {
        if (log.isEnabledFor(Level.ERROR)) {
            log.error(msg, t);
        }
    }

    public void warning(String msg) {
        if (log.isEnabledFor(Level.WARN)) {
            log.warn((Object)msg);
        }
    }

    public void notice(String msg) {
        if (log.isInfoEnabled()) {
            log.info(msg);
        }
    }

    public void info(String msg) {
        if (log.isInfoEnabled()) {
            log.info(msg);
        }
    }

    public void debug(String msg) {
        if (log.isDebugEnabled()) {
            log.debug(msg);
        }
    }

    public void debug(String msg, Throwable t) {
        if (log.isDebugEnabled()) {
            log.debug(msg, t);
        }
    }
}
