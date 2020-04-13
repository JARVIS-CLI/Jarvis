package com.nlstn.jarvis.modules.command.commands;

import java.util.Arrays;
import java.util.List;

import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.modules.job.Job;
import com.nlstn.jarvis.modules.job.JobStatus;
import com.nlstn.jarvis.modules.logging.Logger;

public abstract class Command implements Runnable, Cloneable {

	private String name;
	private CommandDomain domain;
	private List<String> commands;
	protected String[] args;
	private Job job;
	private boolean isInstance;

	public Command(String name, CommandDomain domain, String[] commands) {
		this.name = name;
		this.domain = domain;
		this.commands = Arrays.asList(commands);
		isInstance = false;
	}

	public final void run() {
		if (!isInstance) {
			Logger.error("Tried to run non-instance Command!");
			return;
		}
		if (!validateArguments()) {
			if (job != null)
				job.setStatus(JobStatus.FAILED);
			Logger.info("Invalid arguments for command " + name);
		} else {
			execute();
			if (job != null)
				job.setStatus(JobStatus.FINISHED);
		}
		args = null;
	}

	public Command getInstance() {
		Command result = null;
		try {
			result = (Command) this.clone();
		} catch (CloneNotSupportedException e) {
			Logger.error("Creating an instance of this command failed!", e);
			return null;
		}
		result.isInstance = true;
		return result;
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
