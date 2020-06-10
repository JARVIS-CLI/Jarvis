package com.jarvis.module.modules.command.events;

import com.jarvis.commands.Command;
import com.jarvis.events.JarvisEvent;

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