package com.jarvis.commands.logging;

import com.jarvis.commands.Command;
import com.jarvis.module.modules.command.CommandDomain;
import com.jarvis.module.modules.logging.Level;

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
                    logger.setLogLevel(level);
                }
            }
        } catch (NumberFormatException e) {
            for (Level level : Level.values()) {
                if (level.toString().equals(args[0])) {
                    newLevel = level;
                    logger.setLogLevel(level);
                }
            }

        }
        if (newLevel != null)
            logger.info("Changed Log Level to " + newLevel.toString());
        else
            logger.warning("Level " + args[0] + " does not exist!");
    }

    @Override
    public boolean validateArguments() {
        return args.length == 1;
    }

}