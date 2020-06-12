package com.jarvis.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jarvis.module.modules.command.CommandDomain;
import com.jarvis.module.modules.command.events.CommandEvent;
import com.jarvis.module.modules.command.events.CommandEventHandler;
import com.jarvis.module.modules.command.events.CommandFailedEvent;
import com.jarvis.module.modules.command.events.CommandFinishedEvent;
import com.jarvis.module.modules.command.events.CommandStartedEvent;
import com.jarvis.module.modules.logging.Logger;
import com.jarvis.module.modules.logging.methods.LogMethodFactory;

public abstract class Command implements Runnable, Cloneable {

	private String name;
	private CommandDomain domain;
	private List<String> commands;
	protected Logger logger;

	private String shortDescription;

	protected String[] args;

	private List<CommandEventHandler> eventHandlers;

	public Command(CommandDomain domain, String[] commands) {
		name = this.getClass().getSimpleName();
		this.domain = domain;
		this.commands = Arrays.asList(commands);
		loadCommandInfoFile();
		eventHandlers = new ArrayList<>();
		logger = new Logger();
		logger.addMethod(LogMethodFactory.createStdOutMethod());
	}

	public final void run() {
		if (!validateArguments()) {
			raiseEvent(new CommandFailedEvent(this, "Invalid Arguments"));
			Logger.getRootLogger().info("Invalid arguments for command " + name);
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
		return commands.toArray(new String[0]);
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
			Logger.getRootLogger().error("Failed to load CommandInfoFile " + path, e);
		}
	}
}
