package com.jarvis.module.modules.command.events;

import com.jarvis.commands.Command;

public class CommandFailedEvent extends CommandEvent {

    private String message;

    public CommandFailedEvent(Command command, String message) {
        super(command);
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}