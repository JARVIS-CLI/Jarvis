package com.nlstn.jarvis.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.command.events.CommandEvent;
import com.nlstn.jarvis.module.modules.command.events.CommandEventHandler;
import com.nlstn.jarvis.module.modules.command.events.CommandFailedEvent;
import com.nlstn.jarvis.module.modules.command.events.CommandFinishedEvent;
import com.nlstn.jarvis.module.modules.command.events.CommandStartedEvent;
import com.nlstn.jarvis.module.modules.logging.Logger;

public abstract class Command implements Runnable, Cloneable {

	private String name;
	private CommandDomain domain;
	private List<String> commands;

	private String shortDescription;

	protected String[] args;

	private List<CommandEventHandler> eventHandlers;

	public Command(CommandDomain domain, String[] commands) {
		name = this.getClass().getSimpleName();
		this.domain = domain;
		this.commands = Arrays.asList(commands);
		loadCommandInfoFile();
		eventHandlers = new ArrayList<CommandEventHandler>();
	}

	public final void run() {
		if (!validateArguments()) {
			raiseEvent(new CommandFailedEvent(this, "Invalid Arguments"));
			Logger.info("Invalid arguments for command " + name);
		} else {
			raiseEvent(new CommandStartedEvent(this));
			execute();
			raiseEvent(new CommandFinishedEvent(this));
		}
		args = null;
	}

	public abstract void execute();

	public abstract boolean validateArguments();

	public boolean hasIdentifier(String identifier) {
		return commands.stream().anyMatch(string -> string.equalsIgnoreCase(identifier));
	}

	public void loadArguments(String[] args) {
		this.args = args;
	}

	public String getName() {
		return name;
	}

	public CommandDomain getDomain() {
		return domain;
	}

	public String[] getCommands() {
		return (String[]) commands.toArray();
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void raiseEvent(CommandEvent e) {
		for (CommandEventHandler handler : eventHandlers)
			handler.handleEvent(e);
	}

	public void addEventHandler(CommandEventHandler handler) {
		eventHandlers.add(handler);
	}

	public String toString() {
		return getName() + " - " + getShortDescription();
	}

	private void loadCommandInfoFile() {
		String path = "/commandInfoFiles/" + getName() + ".cif";
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(Command.class.getResourceAsStream(path)))) {
			shortDescription = reader.readLine();
		} catch (Exception e) {
			Logger.error("Failed to load CommandInfoFile " + path, e);
		}
	}
}
