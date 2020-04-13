package com.nlstn.jarvis.modules.command.commands.jobs;

import java.util.Optional;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.modules.command.commands.Command;

public class BackgroundCommand extends Command {

    public BackgroundCommand() {
        super("BackgroundCommand", CommandDomain.JOBS, new String[] { "background", "bg", "job" });
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