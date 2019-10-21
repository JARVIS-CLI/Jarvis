package com.nlstn.jarvis.modules.command.commands.settings;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.logging.Logger;
import com.nlstn.jarvis.modules.command.commands.Command;

public class SaveSettingCommand extends Command {

	public SaveSettingCommand() {
		super("SaveSettingCommand", new String[] { "settings.save" });
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
