package com.nlstn.jarvis.module.modules.command.events;

import com.nlstn.jarvis.commands.Command;

public class CommandStartedEvent extends CommandEvent {

    public CommandStartedEvent(Command command) {
        super(command);
    }

}