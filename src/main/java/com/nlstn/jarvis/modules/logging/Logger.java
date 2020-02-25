package com.nlstn.jarvis.modules.logging;

public class Logger {

    private static Level currentLevel = Level.TRACE;

    public static void log(Level level, String message) {
        if(level.getLevel() >= currentLevel.getLevel())
        System.out.println("[" + level + "]: " + message);
    }

    public static void trace(String message) {
        log(Level.TRACE, message);
    }

    public static void debug(String message) {
        log(Level.DEBUG, message);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void warning(String message) {
        log(Level.WARNING, message);
    }

    public static void error(String message) {
        log(Level.ERROR, message);
    }

    public static void error(String message, Exception e) {
        log(Level.ERROR, message);
        log(Level.ERROR, e.getMessage());
    }

    public static void setLogLevel(Level level) {
        currentLevel = level;
    }

}