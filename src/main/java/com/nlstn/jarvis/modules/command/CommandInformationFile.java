package com.nlstn.jarvis.modules.command;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CommandInformationFile {

    private File commandFolder;

    public CommandInformationFile(File file) {
        this.commandFolder = file;
    }

    public Command loadCommand() {
        Command result = loadClass();

        return result;
    }

    private Command loadClass() {
        Command command = null;
        File file = new File(commandFolder.getAbsolutePath() + "/command.class");
        URLClassLoader classLoader = null;
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[] { url };

            classLoader = new URLClassLoader(urls);

            Class<?> cls = classLoader.loadClass("com.nlstn.jarvis.modules.command.commands.jarvis.ExitCommand");

            Constructor<?>[] constructors = cls.getConstructors();

            Constructor<?> constructor = constructors[0];

            command = (Command) constructor.newInstance();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return command;
    }

    private void loadInfoFile(Command command) {
        File file = new File(commandFolder.getAbsolutePath() + "/command.cif");
        // TODO: Load Command Information from JSON File
    }

}