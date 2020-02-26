package com.nlstn.jarvis.modules.command.commands.settings;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.command.CommandDomain;
import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.logging.Logger;

public class ChangeSettingCommand extends Command {

	public ChangeSettingCommand() {
		super("ChangeSettingCommand", CommandDomain.SETTINGS, new String[] { "change", "set" });
	}

	@Override
	public void execute() {
		if (!ModuleHandler.getSettingsModule().settingExists(args[0]))
			Logger.warning("No setting with key " + args[0] + " exists!");
		else {
			ModuleHandler.getSettingsModule().setSetting(args[0], args[1]);
			Logger.info("Succesfully changed setting " + args[0] + " to " + args[1]);
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length == 2;
	}

}
