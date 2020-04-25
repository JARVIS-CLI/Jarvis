package com.nlstn.jarvis.module.modules.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nlstn.jarvis.module.Module;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.commands.Command;
import com.nlstn.jarvis.module.modules.command.commands.PrintHistoryCommand;
import com.nlstn.jarvis.module.modules.command.commands.jarvis.ExitCommand;
import com.nlstn.jarvis.module.modules.command.commands.jobs.BackgroundCommand;
import com.nlstn.jarvis.module.modules.command.commands.logging.ChangeLogLevelCommand;
import com.nlstn.jarvis.module.modules.command.commands.settings.ChangeSettingCommand;
import com.nlstn.jarvis.module.modules.command.commands.settings.PrintSettingCommand;
import com.nlstn.jarvis.module.modules.command.commands.settings.ReloadSettingCommand;
import com.nlstn.jarvis.module.modules.command.commands.settings.ResetSettingCommand;
import com.nlstn.jarvis.module.modules.command.commands.settings.SaveSettingCommand;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class CommandModule extends Module {

	private CommandModuleImplementation implementation;

	private List<Command> commands;

	public CommandModule() {
		super("CommandModule");
	}

	@Override
	public void preInit() {
		Logger.trace("Loading commands");
		commands = new ArrayList<Command>();

		commands.add(new ExitCommand());
		commands.add(new PrintHistoryCommand());

		commands.add(new ChangeLogLevelCommand());

		commands.add(new ChangeSettingCommand());
		commands.add(new ResetSettingCommand());
		commands.add(new PrintSettingCommand());
		commands.add(new ReloadSettingCommand());
		commands.add(new SaveSettingCommand());

		commands.add(new BackgroundCommand());

		Logger.trace("Finished loading commands");
	}

	@Override
	public void init() {
		implementation = new CommandModuleImplementation(commands);
	}

	@Override
	public void postInit() {
		ModuleHandler.getWorkerModule().submitRunnable(implementation);
	}

	public Optional<Command> getCommand(String[] input) {
		return implementation.getCommand(input);
	}

	public List<Command> getCommandHistory() {
		return implementation.getCommandHistory();
	}

	@Override
	public void shutdown() {
		implementation.shutdown();

	}

}
