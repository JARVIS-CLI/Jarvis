package com.jarvis.module.modules.command.events;

import com.jarvis.commands.Command;

public class CommandStartedEvent extends CommandEvent {

    public CommandStartedEvent(Command command) {
        super(command);
    }

}