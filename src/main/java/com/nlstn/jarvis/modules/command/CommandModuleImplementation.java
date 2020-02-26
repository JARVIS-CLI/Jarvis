package com.nlstn.jarvis.modules.command;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.logging.Logger;

public class CommandModuleImplementation implements Runnable {

	private List<Command> commands;
	private volatile boolean running = true;

	public CommandModuleImplementation(List<Command> commands) {
		this.commands = commands;
	}

	public void run() {

		Scanner scanner = new Scanner(System.in);
		while (running) {
			String input = scanner.nextLine();
			String[] split = input.split(" ");
			String commandIdentifier = split[0];
			String[] args = new String[split.length - 1];
			System.arraycopy(split, 1, args, 0, args.length);

			Optional<Command> commandMatch = Optional.empty();
			if (commandIdentifier.contains(".")) {
				String[] commandIdentifierSplit = commandIdentifier.split(".");
				if (commandIdentifierSplit.length != 2) {
					Logger.warning("Invalid command format! Multiple domains found");
					continue;
				}
				CommandDomain domain = CommandDomain.getByString(commandIdentifierSplit[0]);
				commandMatch = commands.stream().filter(
						command -> command.getDomain() == domain && command.hasIdentifier(commandIdentifierSplit[1]))
						.findAny();
			} else {
				List<Object> matchingCommands = Arrays.asList(
						commands.stream().filter(command -> command.hasIdentifier(commandIdentifier)).toArray());
				if (matchingCommands.size() == 0) {
					Logger.warning("Command " + commandIdentifier + " not found");
				} else if (matchingCommands.size() == 1) {
					commandMatch = Optional.of((Command) matchingCommands.get(0));
				} else {
					Logger.info("Multiple commands found:");
					for (Object object : matchingCommands) {
						Command currentCommand = (Command) object;
						Logger.info(currentCommand.getDomain().toString() + "." + currentCommand.getCommands()[0]);
					}

				}
			}
			commandMatch.ifPresent(command -> command.loadArguments(args));
			commandMatch.ifPresent(this::executeCommand);
			if (!commandMatch.isPresent())
				Logger.warning("Command " + commandIdentifier + " not found!");
		}
		Logger.trace("Exited CommandModuleImplementation Loop");
		scanner.close();
	}

	void executeCommand(Command command) {
		ModuleHandler.getWorkerModule().submitRunnable(command);
	}

	public void shutdown() {
		Logger.trace("Shutting down CommandModuleImplementation");
		running = false;
	}

}
