package com.nlstn.jarvis.modules.command.commands.jarvis;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import com.nlstn.jarvis.logging.Level;
import com.nlstn.jarvis.logging.Logger;
import com.nlstn.jarvis.modules.command.commands.Command;

public class ChangeLogLevelCommand extends Command {

	public ChangeLogLevelCommand() {
		super("ChangeLogLevelCommand", new String[] { "jarvis.changeLogLevel" });
	}

	@Override
	public void execute() {
		Stream<Level> stream = Arrays.asList(Level.values()).parallelStream();
		try {
			int logLevel = Integer.parseInt(args[0]);
			Optional<Level> optLevel = stream.filter(level -> level.getID() == logLevel).findAny();
			optLevel.ifPresent(level -> Logger.setLogLevel(level));
		}
		catch (NumberFormatException e) {
			Optional<Level> optLevel = stream.filter(level -> level.getPrefix().equalsIgnoreCase(args[0])).findAny();
			optLevel.ifPresent(level -> Logger.setLogLevel(level));
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 1;
	}

}
