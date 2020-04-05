package com.nlstn.jarvis.modules.command;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.Module;
import com.nlstn.jarvis.modules.logging.Logger;

public class CommandModule extends Module {

	private CommandModuleImplementation implementation;

	public CommandModule() {
		super("CommandModule");
	}

	@Override
	public void preInit() {
		Logger.trace("Loading commands");

		CommandLoader.loadCommands();

		Logger.trace("Finished loading commands");
	}

	@Override
	public void init() {
		implementation = new CommandModuleImplementation();
	}

	@Override
	public void postInit() {
		ModuleHandler.getWorkerModule().submitRunnable(implementation);
	}

	@Override
	public void shutdown() {
		implementation.shutdown();

	}

}
