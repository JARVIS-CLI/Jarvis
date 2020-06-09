package com.nlstn.jarvis.commands.logging;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Level;
import com.nlstn.jarvis.module.modules.logging.Logger;

/**
 * The {@code ChangeLogLevelCommand} is used to change the log level of the
 * global logger
 */
public class ChangeLogLevelCommand extends Command {

    public ChangeLogLevelCommand() {
        super(CommandDomain.SETTINGS, new String[] { "setLevel", "changeLevel" });
    }

    @Override
    public void execute() {
        Level newLevel = null;
        try {
            int intLevel = Integer.parseInt(args[0]);
            for (Level level : Level.values()) {
                if (level.getLevel() == intLevel) {
                    newLevel = level;
                    Logger.getRootLogger().setLogLevel(level);
                    return;
                }
            }
            Logger.getRootLogger().warning("Level " + intLevel + " does not exist!");
        } catch (NumberFormatException e) {
            for (Level level : Level.values()) {
                if (level.toString().equals(args[0])) {
                    newLevel = level;
                    Logger.getRootLogger().setLogLevel(level);
                    return;
                }
            }
            Logger.getRootLogger().warning("Level " + args[0] + " does not exist!");
        }
        if (newLevel != null)
            Logger.getRootLogger().info("Changed Log Level to " + newLevel.toString());
    }

    @Override
    public boolean validateArguments() {
        return args.length == 1;
    }

}