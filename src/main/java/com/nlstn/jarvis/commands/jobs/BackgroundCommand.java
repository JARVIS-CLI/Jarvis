package com.nlstn.jarvis.commands.jobs;

import java.util.Optional;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;

/**
 * The {@code BackgroundCommand} is used to execute a command in the background.
 */
public class BackgroundCommand extends Command {

    public BackgroundCommand() {
        super(CommandDomain.JOBS, new String[] { "background", "bg", "job" });
    }

    @Override
    public void execute() {
        Optional<Command> commandMatch = ModuleHandler.getCommandModule().getCommand(args);
        commandMatch.ifPresent(command -> ModuleHandler.getJobModule().executeImmediately(command));
    }

    @Override
    public boolean validateArguments() {
        return args.length >= 1;
    }

}