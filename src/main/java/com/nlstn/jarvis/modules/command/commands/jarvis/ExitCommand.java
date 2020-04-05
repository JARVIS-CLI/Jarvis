package com.nlstn.jarvis.modules.command.commands.jarvis;

import com.nlstn.jarvis.Jarvis;
import com.nlstn.jarvis.modules.command.Command;
import com.nlstn.jarvis.modules.command.CommandDomain;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("ExitCommand", CommandDomain.JARVIS, new String[] { "exit", "shutdown", "close", "stop" });
	}

	@Override
	public void execute() {
		Jarvis.shutdown();
	}

	public boolean validateArguments() {
		return args.length == 0;
	}

}
