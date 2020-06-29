package com.jarvis.module.modules.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.logging.Logger;

public class CommandModuleImplementation implements Runnable {

	/**
	 * The statistics key for counting the executed commands
	 */
	private static String STATISTICS_KEY_EXECUTE = "CommandModule.EXECUTE_COMMAND";

	/**
	 * The list of available commands
	 */
	private List<Command> commands;

	/**
	 * A list of recently executed commands
	 */
	private List<Command> recentCommands;

	/**
	 * Whether this implementation is still running or not
	 */
	private volatile boolean running = true;

	public CommandModuleImplementation(List<Command> commands) {
		this.commands = commands;
		recentCommands = new ArrayList<>();
	}

	public void run() {

		Scanner scanner = new Scanner(System.in);
		while (running) {
			String input = scanner.nextLine();
			if (input == null || input.length() == 0)
				continue;
			String[] split = input.split(" ");

			Optional<Command> commandOpt = getCommand(split);

			commandOpt.ifPresent(command -> ModuleHandler.getWorkerModule().submitRunnable(command));
			commandOpt.ifPresent(this::addToRecentCommands);
			ModuleHandler.getStatisticsModule().addRecord(STATISTICS_KEY_EXECUTE);
			if (!commandOpt.isPresent())
				Logger.getRootLogger().warning("Command " + split[0] + " not found!");
		}
		Logger.getRootLogger().trace("Exited CommandModuleImplementation Loop");
		scanner.close();
	}

	public Optional<Command> getCommand(String[] input) {
		String commandIdentifier = input[0];
		String[] args = new String[input.length - 1];
		System.arraycopy(input, 1, args, 0, args.length);

		Optional<Command> commandMatch = Optional.empty();
		if (commandIdentifier.contains(".")) {
			String[] commandIdentifierSplit = commandIdentifier.split("\\.");
			if (commandIdentifierSplit.length != 2) {
				Logger.getRootLogger().warning("Invalid command format! Multiple domains found");
				return commandMatch;
			}
			CommandDomain domain = CommandDomain.getByString(commandIdentifierSplit[0]);
			commandMatch = commands.stream().filter(
					command -> command.getDomain() == domain && command.hasIdentifier(commandIdentifierSplit[1]))
					.findAny();
		} else {
			List<Object> matchingCommands = Arrays
					.asList(commands.stream().filter(command -> command.hasIdentifier(commandIdentifier)).toArray());
			if (matchingCommands.isEmpty()) {
				Logger.getRootLogger().warning("Command " + commandIdentifier + " not found");
			} else if (matchingCommands.size() == 1) {
				commandMatch = Optional.of((Command) matchingCommands.get(0));
			} else {
				Logger.getRootLogger().info("Multiple commands found:");
				for (Object object : matchingCommands) {
					Command currentCommand = (Command) object;
					Logger.getRootLogger()
							.info(currentCommand.getDomain().toString() + "." + currentCommand.getCommands()[0]);
				}

			}
		}
		commandMatch.ifPresent(command -> command.loadArguments(args));
		return commandMatch;
	}

	private void addToRecentCommands(Command command) {
		recentCommands.add(command);
		int maxSize = Integer.parseInt(ModuleHandler.getSettingsModule().getSetting("commands.maxRecentCommandsSize"));
		if (recentCommands.size() >= maxSize)
			recentCommands.remove(0);
	}

	public List<Command> getCommandHistory() {
		return recentCommands;
	}

	public void shutdown() {
		Logger.getRootLogger().trace("Shutting down CommandModuleImplementation");
		running = false;
	}

}
