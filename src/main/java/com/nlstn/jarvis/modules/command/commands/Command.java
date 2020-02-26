package com.nlstn.jarvis.modules.command.commands;

import java.util.Arrays;
import java.util.List;

import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.modules.logging.Logger;

public abstract class Command implements Runnable {

	private String			name;
	private CommandDomain   domain;
	private List<String>	commands;
	protected String[]		args;

	public Command(String name, CommandDomain domain, String[] commands) {
		this.name = name;
		this.domain = domain;
		this.commands = Arrays.asList(commands);
	}

	public final void run() {
		if(!validateArguments())
			Logger.info("Invalid arguments for command " + name);
		else
			execute();
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

}
