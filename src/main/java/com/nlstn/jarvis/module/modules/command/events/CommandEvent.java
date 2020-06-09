package com.nlstn.jarvis.module.modules.command.events;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.events.JarvisEvent;

public abstract class CommandEvent extends JarvisEvent {

    private Command command;

    public CommandEvent(Command command) {
        this.command = command;
    }

    /**
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

}