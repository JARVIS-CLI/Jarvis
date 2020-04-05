package com.nlstn.jarvis.modules.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nlstn.jarvis.Jarvis;

public class CommandLoader {

    private static List<Command> commands;

    public static void loadCommands() {
        commands = new ArrayList<Command>();
        File commandFolder = new File(Jarvis.PATH + "/commands");
        File[] files = commandFolder.listFiles();

        for (File file : files) {
            if (file.isDirectory() == true)
                commands.add(new CommandInformationFile(file).getCommand());
        }
    }

    public static List<Command> getCommands() {
        return commands;
    }
}