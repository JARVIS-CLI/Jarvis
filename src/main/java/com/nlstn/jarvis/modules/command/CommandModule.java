package com.nlstn.jarvis.modules.command;

import java.util.ArrayList;
import java.util.List;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.logging.Logger;
import com.nlstn.jarvis.modules.Module;
import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.command.commands.jarvis.ChangeLogLevelCommand;
import com.nlstn.jarvis.modules.command.commands.jarvis.ExitCommand;
import com.nlstn.jarvis.modules.command.commands.settings.ChangeSettingCommand;
import com.nlstn.jarvis.modules.command.commands.settings.PrintSettingCommand;
import com.nlstn.jarvis.modules.command.commands.settings.ReloadSettingCommand;
import com.nlstn.jarvis.modules.command.commands.settings.ResetSettingCommand;
import com.nlstn.jarvis.modules.command.commands.settings.SaveSettingCommand;

public class CommandModule extends Module {

	private CommandModuleImplementation	implementation;

	private List<Command>				commands;

	public CommandModule() {
		super("CommandModule");
	}

	@Override
	public void preInit() {
		Logger.trace("Loading commands");
		commands = new ArrayList<Command>();

		commands.add(new ExitCommand());
		commands.add(new ChangeLogLevelCommand());

		commands.add(new ChangeSettingCommand());
		commands.add(new ResetSettingCommand());
		commands.add(new PrintSettingCommand());
		commands.add(new ReloadSettingCommand());
		commands.add(new SaveSettingCommand());

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

	@Override
	public void shutdown() {
		implementation.shutdown();

	}

}
