package com.nlstn.jarvis.commands.settings;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class SaveSettingsCommand extends Command {

	public SaveSettingsCommand() {
		super(CommandDomain.SETTINGS, new String[] { "save" });
	}

	@Override
	public void execute() {
		Logger.info("Saving settings");
		ModuleHandler.getSettingsModule().save();
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0;
	}

}
