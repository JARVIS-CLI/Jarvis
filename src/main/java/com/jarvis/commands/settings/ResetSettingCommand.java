package com.jarvis.commands.settings;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;
import com.jarvis.module.modules.logging.Logger;

public class ResetSettingCommand extends Command {

	public ResetSettingCommand() {
		super(CommandDomain.SETTINGS, new String[] { "reset" });
	}

	@Override
	public void execute() {
		if (args.length == 0) {
			logger.info("Resetting all settings to default value");
			ModuleHandler.getSettingsModule().reset();
		} else {
			logger.info("Resetting setting " + args[0] + " to default");
			boolean success = ModuleHandler.getSettingsModule().reset(args[0]);
			if (!success)
				Logger.getRootLogger().info("Setting " + args[0] + " was not found!");
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0 || args.length == 1;
	}

}
