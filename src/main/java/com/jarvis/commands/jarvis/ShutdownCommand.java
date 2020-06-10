package com.jarvis.commands.jarvis;

import com.jarvis.Jarvis;
import com.jarvis.commands.Command;
import com.jarvis.module.modules.command.CommandDomain;

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
