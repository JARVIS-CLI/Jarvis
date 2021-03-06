package com.jarvis.commands.settings;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;

public class ChangeSettingCommand extends Command {

	public ChangeSettingCommand() {
		super(CommandDomain.SETTINGS, new String[] { "change", "set" });
	}

	@Override
	public void execute() {
		if (!ModuleHandler.getSettingsModule().settingExists(args[0]))
			logger.warning("No setting with key " + args[0] + " exists!");
		else {
			ModuleHandler.getSettingsModule().setSetting(args[0], args[1]);
			logger.info("Succesfully changed setting " + args[0] + " to " + args[1]);
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 2;
	}

}
