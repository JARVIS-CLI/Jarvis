package com.jarvis.module.modules.logging.methods;

public class LogMethodFactory {

    private LogMethodFactory() {
    }

    public static LogMethod createStdOutMethod() {
        return System.out::println;
    }

}