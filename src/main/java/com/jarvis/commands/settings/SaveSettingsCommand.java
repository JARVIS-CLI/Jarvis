package com.jarvis.commands.settings;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.command.CommandDomain;
import com.jarvis.module.modules.logging.Logger;

public class SaveSettingsCommand extends Command {

	public SaveSettingsCommand() {
		super(CommandDomain.SETTINGS, new String[] { "save" });
	}

	@Override
	public void execute() {
		Logger.getRootLogger().info("Saving settings");
		ModuleHandler.getSettingsModule().save();
	}

	@Override
	public boolean validateArguments() {
		return args.length == 0;
	}

}
