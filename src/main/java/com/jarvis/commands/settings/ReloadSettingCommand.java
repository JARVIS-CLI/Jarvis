package com.jarvis.commands.settings;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;

public class ReloadSettingCommand extends Command {

	public ReloadSettingCommand() {
		super(CommandDomain.SETTINGS, new String[] { "reload" });
	}

	@Override
	public void execute() {
		if (args.length == 0) {
			logger.info("Reloading all settings from file");
			ModuleHandler.getSettingsModule().reload();
		} else {
			logger.info("Reloading setting " + args[0] + " from file");
			ModuleHandler.getSettingsModule().reload(args[0]);
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0 || args.length == 1;
	}

}
