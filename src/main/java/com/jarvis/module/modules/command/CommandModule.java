package com.jarvis.module.modules.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jarvis.Jarvis;
import com.jarvis.commands.Command;
import com.jarvis.events.JarvisShutdownEvent;
import com.jarvis.module.Module;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.logging.Logger;

import org.reflections.Reflections;

/**
 * This module makes it possible to execute commands from the command line
 */
public class CommandModule extends Module {

	/**
	 * The executable that handles the logic of the command module
	 */
	private CommandModuleImplementation implementation;

	/**
	 * A list of all loaded commands
	 */
	private List<Command> commands;

	public CommandModule() {
		super();
	}

	@Override
	public void preInit() {
		Logger.getRootLogger().trace("Loading commands");
		commands = new ArrayList<>();

		Reflections reflections = new Reflections("com.jarvis.commands");
		Set<Class<? extends Command>> commandList = reflections.getSubTypesOf(Command.class);
		commandList.forEach(command -> {
			try {
				commands.add(command.getConstructor().newInstance());
			} catch (Exception e) {
				Logger.getRootLogger().error("Failed to instantiate command");
			}
		});

		Logger.getRootLogger().trace("Finished loading commands");
	}

	@Override
	public void init() {
		implementation = new CommandModuleImplementation(commands);
		Jarvis.addEventHandler(e -> {
			if (e instanceof JarvisShutdownEvent)
				implementation.shutdown();
		});
	}

	@Override
	public void postInit() {
		ModuleHandler.getWorkerModule().submitRunnable(implementation);
	}

	/**
	 * @return All commands currently available to use
	 */
	public List<Command> getLoadedCommands() {
		return commands;
	}

	/**
	 * Searches for a matching command and loads it up with the provided arguments
	 * 
	 * @param input The command identifier + it's arguments
	 * @return the loaded command, ready to execute
	 */
	public Optional<Command> getCommand(String[] input) {
		return implementation.getCommand(input);
	}

	/**
	 * Get the history of recently exercuted commands
	 * 
	 * @return The recently used commands
	 */
	public List<Command> getCommandHistory() {
		return implementation.getCommandHistory();
	}

}
