package com.jarvis.module.modules.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jarvis.Jarvis;
import com.jarvis.commands.Command;
import com.jarvis.commands.commands.PrintCommandsCommand;
import com.jarvis.commands.commands.PrintHistoryCommand;
import com.jarvis.commands.jarvis.ShutdownCommand;
import com.jarvis.commands.jobs.BackgroundCommand;
import com.jarvis.commands.logging.ChangeLogLevelCommand;
import com.jarvis.commands.settings.ChangeSettingCommand;
import com.jarvis.commands.settings.PrintSettingCommand;
import com.jarvis.commands.settings.ReloadSettingCommand;
import com.jarvis.commands.settings.ResetSettingCommand;
import com.jarvis.commands.settings.SaveSettingsCommand;
import com.jarvis.commands.statistics.PrintStatisticCommand;
import com.jarvis.events.JarvisShutdownEvent;
import com.jarvis.module.Module;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.logging.Logger;

public class CommandModule extends Module {

	private CommandModuleImplementation implementation;

	private List<Command> commands;

	public CommandModule() {
		super();
	}

	@Override
	public void preInit() {
		Logger.getRootLogger().trace("Loading commands");
		commands = new ArrayList<>();

		commands.add(new ShutdownCommand());
		commands.add(new PrintHistoryCommand());

		commands.add(new ChangeLogLevelCommand());

		commands.add(new ChangeSettingCommand());
		commands.add(new ResetSettingCommand());
		commands.add(new PrintSettingCommand());
		commands.add(new ReloadSettingCommand());
		commands.add(new SaveSettingsCommand());

		commands.add(new BackgroundCommand());

		commands.add(new PrintStatisticCommand());
		commands.add(new PrintCommandsCommand());

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

	public List<Command> getLoadedCommands() {
		return commands;
	}

	public Optional<Command> getCommand(String[] input) {
		return implementation.getCommand(input);
	}

	public List<Command> getCommandHistory() {
		return implementation.getCommandHistory();
	}

}
