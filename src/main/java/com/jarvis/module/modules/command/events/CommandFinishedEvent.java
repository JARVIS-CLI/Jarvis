package com.jarvis.module.modules.command.events;

import com.jarvis.commands.Command;

public class CommandFinishedEvent extends CommandEvent {

    public CommandFinishedEvent(Command command) {
        super(command);
    }

}