package com.nlstn.jarvis.commands.commands;

import java.util.List;
import java.util.ListIterator;

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
        ListIterator<Command> iterator = recentCommands.listIterator(recentCommands.size());

        while (iterator.hasPrevious())
            Logger.info(iterator.previous().getName());
    }

    @Override
    public boolean validateArguments() {
        return args.length == 0;
    }

}