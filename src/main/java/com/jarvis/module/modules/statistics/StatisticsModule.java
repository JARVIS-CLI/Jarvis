package com.jarvis.module.modules.statistics;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.jarvis.Jarvis;
import com.jarvis.events.JarvisShutdownEvent;
import com.jarvis.module.Module;
import com.jarvis.module.modules.logging.Logger;

public class StatisticsModule extends Module {

    private Properties properties;

    public StatisticsModule() {
        super();
    }

    @Override
    public void preInit() {
        File file = new File(Jarvis.PATH + "/default.stat");
        properties = new Properties();
        if (file.exists()) {
            try {
                properties.load(new BufferedInputStream(new FileInputStream(file)));
            } catch (IOException e) {
                Logger.getRootLogger().error("Failed to load statistics", e);
            }
        }
        Jarvis.addEventHandler(e -> {
            if (e instanceof JarvisShutdownEvent)
                save();
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    public void addRecord(String name) {
        Object property = properties.get(name);
        int value = 0;
        if (property != null) {
            value = Integer.parseInt((String) property);
        }
        value++;
        properties.setProperty(name, String.valueOf(value));
    }

    public int getStatisticsValue(String name) {
        return Integer.parseInt((String) properties.get(name));
    }

    private void save() {
        BufferedOutputStream stream;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(Jarvis.PATH + "/default.stat"));
            properties.store(stream, null);

            stream.close();
        } catch (FileNotFoundException e) {
            Logger.getRootLogger().error("FileNotFoundException while saving statistics!", e);
        } catch (IOException e) {
            Logger.getRootLogger().error("IOException while saving statistics!", e);
        }
    }

}