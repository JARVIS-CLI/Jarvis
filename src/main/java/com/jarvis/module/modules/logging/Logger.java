package com.jarvis.module.modules.logging;

public class Logger {

    private static Logger rootLogger;

    static {
        rootLogger = new Logger();
    }

    public static Logger getRootLogger() {
        return rootLogger;
    }

    //////////////////////////////////////////

    private Level currentLevel = Level.INFO;

    public void log(Level level, String message) {
        if (level.getLevel() >= currentLevel.getLevel())
            System.out.println("[" + level + "]: " + message);
    }

    public void trace(String message) {
        log(Level.TRACE, message);
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String message, Exception e) {
        log(Level.ERROR, message);
        log(Level.ERROR, e.getMessage());
    }

    public void setLogLevel(Level level) {
        currentLevel = level;
    }
}