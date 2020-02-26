package com.nlstn.jarvis.modules.command.commands.logging;

import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.logging.Level;
import com.nlstn.jarvis.modules.logging.Logger;

public class ChangeLogLevelCommand extends Command {

    public ChangeLogLevelCommand() {
        super("ChangeLogLevelCommand", CommandDomain.SETTINGS,
                new String[] { "logger.setLevel", "logger.changeLevel" });
    }

    @Override
    public void execute() {
        Level newLevel = null;
        try {
            int intLevel = Integer.parseInt(args[0]);
            for (Level level : Level.values()) {
                if (level.getLevel() == intLevel) {
                    newLevel = level;
                    Logger.setLogLevel(level);
                    return;
                }
            }
            Logger.warning("Level " + intLevel + " does not exist!");
        } catch (NumberFormatException e) {
            for (Level level : Level.values()) {
                if (level.toString().equals(args[0])) {
                    newLevel = level;
                    Logger.setLogLevel(level);
                    return;
                }
            }
            Logger.warning("Level " + args[0] + " does not exist!");
        }
        if (newLevel != null)
            Logger.info("Changed Log Level to " + newLevel.toString());
    }

    @Override
    public boolean validateArguments() {
        return args.length == 1;
    }

}