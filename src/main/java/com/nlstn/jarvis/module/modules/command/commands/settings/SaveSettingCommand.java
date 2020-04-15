package com.nlstn.jarvis.module.modules.command.commands.settings;

import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.command.commands.Command;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class SaveSettingCommand extends Command {

	public SaveSettingCommand() {
		super("SaveSettingCommand", CommandDomain.SETTINGS, new String[] { "save" });
	}

	@Override
	public void execute() {
		Logger.info("Saving setting");
		ModuleHandler.getSettingsModule().save();
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0;
	}

}