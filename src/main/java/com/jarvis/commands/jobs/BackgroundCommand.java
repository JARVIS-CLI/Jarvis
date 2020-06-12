package com.jarvis.commands.jobs;

import java.util.Optional;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;

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
        commandMatch.ifPresent(command -> {
            command.setBackground(true);
            ModuleHandler.getJobModule().executeImmediately(command);
            logger.info("Planned as background job");
        });
    }

    @Override
    public boolean validateArguments() {
        return args.length >= 1;
    }

}