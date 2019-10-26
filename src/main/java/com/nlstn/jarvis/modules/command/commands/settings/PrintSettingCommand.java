package com.nlstn.jarvis.modules.command.commands.settings;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.logging.Logger;

public class PrintSettingCommand extends Command {

	public PrintSettingCommand() {
		super("PrintSettingCommand", new String[] { "settings.print" });
	}

	public void execute() {
		String value = ModuleHandler.getSettingsModule().getSetting(args[0]);
		if(value == null)
			Logger.info("Setting not found!");
		else
			Logger.info("Setting " + args[0] + " has value " + value);
	}

	@Override
	public boolean validateArguments() {
		return args.length == 1;
	}

}
