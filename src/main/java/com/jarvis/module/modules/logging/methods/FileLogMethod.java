package com.jarvis.module.modules.logging.methods;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.jarvis.Jarvis;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.events.ModuleShutdownEvent;
import com.jarvis.module.modules.logging.Logger;

public class FileLogMethod implements LogMethod {

    private BufferedOutputStream stream;

    public FileLogMethod(String path) {

        File file = new File(Jarvis.PATH + "/logs/" + path);
        try {
            stream = new BufferedOutputStream(new FileOutputStream(file));
            ModuleHandler.getLoggingModule().addEventHandler(e -> {
                if (e instanceof ModuleShutdownEvent) {
                    try {
                        stream.close();
                    } catch (Exception e1) {
                        Logger.getRootLogger().error("Failed open log file", e1);
                    }
                }
            });
        } catch (Exception e) {
            Logger.getRootLogger().error("Failed open log file", e);
        }
    }

    @Override
    public void log(String log) {

    }

}