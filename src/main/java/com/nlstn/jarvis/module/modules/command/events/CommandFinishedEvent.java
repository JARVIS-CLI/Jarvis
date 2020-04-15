package com.nlstn.jarvis.module.modules.command.events;

import com.nlstn.jarvis.module.modules.command.commands.Command;

public class CommandFinishedEvent extends CommandEvent {

    public CommandFinishedEvent(Command command) {
        super("CommandFinishedEvent", command);
    }

}