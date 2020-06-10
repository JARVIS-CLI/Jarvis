package com.jarvis.module.modules.logging;

import java.util.ArrayList;
import java.util.List;

import com.jarvis.module.modules.logging.methods.LogMethod;
import com.jarvis.module.modules.logging.methods.LogMethodFactory;

public class Logger {

    private static Logger rootLogger;

    static {
        rootLogger = new Logger();
        rootLogger.addMethod(LogMethodFactory.createStdOutMethod());
    }

    public static Logger getRootLogger() {
        return rootLogger;
    }

    //////////////////////////////////////////

    private Level currentLevel = Level.INFO;
    private List<LogMethod> methods;

    public Logger() {
        methods = new ArrayList<>();
    }

    public void log(Level level, String message) {
        if (!checkLevel(level))
            return;
        logInt(level, message);
    }

    public void log(Level level, Object object) {
        if (!checkLevel(level))
            return;
        if (object.getClass().isArray()) {
            StringBuilder builder = new StringBuilder();
            Object[] array = (Object[]) object;
            for (Object subObj : array) {
                builder.append(subObj.toString()).append("\n");
            }
            logInt(level, builder.toString());
        } else {
            logInt(level, object.toString());
        }
    }

    public void addMethod(LogMethod method) {
        methods.add(method);
    }

    public void setLogLevel(Level level) {
        currentLevel = level;
    }

    private void logInt(Level level, String log) {
        String logMessage = getPrefix(level) + log;
        for (LogMethod method : methods)
            method.log(logMessage);
    }

    private boolean checkLevel(Level level) {
        return level.getLevel() >= currentLevel.getLevel();
    }

    private String getPrefix(Level level) {
        return "[" + level + "]";
    }

    ///////////////////////////////////////////////////////////

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
        log(Level.ERROR, e.getStackTrace());
    }
}