package com.nlstn.jarvis.commands.settings;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class ReloadSettingCommand extends Command {

	public ReloadSettingCommand() {
		super(CommandDomain.SETTINGS, new String[] { "reload" });
	}

	@Override
	public void execute() {
		if (args.length == 0) {
			Logger.info("Reloading all settings from file");
			ModuleHandler.getSettingsModule().reload();
		} else {
			Logger.info("Reloading setting " + args[0] + " from file");
			ModuleHandler.getSettingsModule().reload(args[0]);
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0 || args.length == 1;
	}

}
