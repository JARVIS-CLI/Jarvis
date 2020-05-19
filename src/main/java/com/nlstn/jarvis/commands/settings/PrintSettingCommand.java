package com.nlstn.jarvis.commands.settings;

import java.util.List;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.CommandDomain;
import com.nlstn.jarvis.module.modules.logging.Logger;
import com.nlstn.jarvis.module.modules.settings.Setting;

public class PrintSettingCommand extends Command {

	public PrintSettingCommand() {
		super(CommandDomain.SETTINGS, new String[] { "print", "printSetting" });
	}

	public void execute() {
		if (args.length == 0 || args[0].equals("all")) {
			List<Setting> settings = ModuleHandler.getSettingsModule().getSettings();
			for (Setting setting : settings) {
				Logger.info("Setting " + setting.getKey() + " has value " + setting.getValue());
			}
		} else {
			String value = ModuleHandler.getSettingsModule().getSetting(args[0]);
			if (value == null)
				Logger.info("Setting not found!");
			else
				Logger.info("Setting " + args[0] + " has value " + value);
		}
	}

	@Override
	public boolean validateArguments() {
		return args.length <= 1;
	}

}
