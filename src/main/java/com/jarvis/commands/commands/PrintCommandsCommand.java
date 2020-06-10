package com.jarvis.commands.commands;

import java.util.List;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;
import com.jarvis.module.modules.logging.Logger;

/**
 * The {@code PrintCommandsCommand} prints all available commands.
 */
public class PrintCommandsCommand extends Command {

    public PrintCommandsCommand() {
        super(CommandDomain.COMMANDS, new String[] { "print", "printCommands", "listCommands" });
    }

    public void execute() {
        List<Command> commands = ModuleHandler.getCommandModule().getLoadedCommands();

        for (Command command : commands) {
            Logger.getRootLogger().info(command.toString());
        }
    }

    public boolean validateArguments() {
        return args.length == 0;
    }

}