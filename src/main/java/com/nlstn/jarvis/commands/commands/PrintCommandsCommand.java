package com.nlstn.jarvis.commands.commands;

import java.util.List;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Logger;

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
            Logger.info(command.toString());
        }
    }

    public boolean validateArguments() {
        return args.length == 0;
    }

}