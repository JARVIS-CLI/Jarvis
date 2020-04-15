package com.nlstn.jarvis.modules.command.events;

import com.nlstn.jarvis.modules.command.commands.Command;

public class CommandFinishedEvent extends CommandEvent {

    public CommandFinishedEvent(Command command) {
        super("CommandFinishedEvent", command);
    }

}