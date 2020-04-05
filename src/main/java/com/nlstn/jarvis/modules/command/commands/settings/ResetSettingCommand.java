package com.nlstn.jarvis.modules.command.commands.settings;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.command.Command;
import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.modules.logging.Logger;

public class ResetSettingCommand extends Command {

	public ResetSettingCommand() {
		super("ResetSettingCommand", CommandDomain.SETTINGS, new String[] { "reset" });
	}

	@Override
	public void execute() {
		if (args.length == 0) {
			Logger.info("Resetting all settings to default value");
			ModuleHandler.getSettingsModule().reset();
		} else {
			Logger.info("Resetting setting " + args[0] + " to default");
			boolean success = ModuleHandler.getSettingsModule().reset(args[0]);
			if (!success)
				Logger.info("Setting " + args[0] + " was not found!");
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0 || args.length == 1;
	}

}
