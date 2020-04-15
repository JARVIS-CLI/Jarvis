package com.nlstn.jarvis.module.modules.command.events;

import com.nlstn.jarvis.module.modules.command.commands.Command;

public class CommandFailedEvent extends CommandEvent {

    private String message;

    public CommandFailedEvent(Command command, String message) {
        super("CommandFailedEvent", command);
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}