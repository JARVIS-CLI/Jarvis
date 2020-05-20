package com.nlstn.jarvis.commands.jarvis;

import com.nlstn.jarvis.Jarvis;
import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.modules.command.CommandDomain;

/**
 * The {@code ShutdownCommand} shuts down Jarvis
 */
public class ShutdownCommand extends Command {

	public ShutdownCommand() {
		super(CommandDomain.JARVIS, new String[] { "exit", "shutdown", "close", "stop" });
	}

	@Override
	public void execute() {
		Jarvis.shutdown();
	}

	public boolean validateArguments() {
		return args.length == 0;
	}

}
