package com.jarvis.module.modules.logging.methods;

public class LogMethodFactory {

    private LogMethodFactory() {
    }

    public static LogMethod createStdOutMethod() {
        return System.out::println;
    }

    public static LogMethod createFileMethod(String path) {
        return new FileLogMethod(path);
    }

}