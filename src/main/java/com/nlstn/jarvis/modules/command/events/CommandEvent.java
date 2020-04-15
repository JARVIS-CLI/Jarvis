package com.nlstn.jarvis.modules.command.events;

import com.nlstn.jarvis.JarvisEvent;
import com.nlstn.jarvis.modules.command.commands.Command;

public abstract class CommandEvent extends JarvisEvent {

    private Command command;

    public CommandEvent(String name, Command command) {
        super(name);
        this.command = command;
    }

    /**
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

}