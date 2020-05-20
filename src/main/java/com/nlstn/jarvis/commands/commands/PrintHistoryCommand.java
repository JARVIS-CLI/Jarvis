package com.nlstn.jarvis.commands.commands;

import java.util.List;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Logger;

/**
 * The {@code PrintHistoryCommand} prints the last used commands. It has no
 * arguments
 */
public class PrintHistoryCommand extends Command {

    public PrintHistoryCommand() {
        super(CommandDomain.COMMANDS, new String[] { "history" });
    }

    @Override
    public void execute() {
        List<Command> recentCommands = ModuleHandler.getCommandModule().getCommandHistory();
        for (Command command : recentCommands) {
            Logger.info(command.getName());
        }
    }

    @Override
    public boolean validateArguments() {
        return args.length == 0;
    }

}