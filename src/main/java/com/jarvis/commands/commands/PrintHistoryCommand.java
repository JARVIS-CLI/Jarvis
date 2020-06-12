package com.jarvis.commands.commands;

import java.util.List;
import java.util.ListIterator;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;

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
        logger.info(iterator.previous().getName());
    }

    @Override
    public boolean validateArguments() {
        return args.length == 0;
    }

}