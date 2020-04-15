package com.nlstn.jarvis.modules.command.events;

import com.nlstn.jarvis.modules.command.commands.Command;

public class CommandStartedEvent extends CommandEvent {

    public CommandStartedEvent(Command command) {
        super("CommandStartedEvent", command);
    }

}