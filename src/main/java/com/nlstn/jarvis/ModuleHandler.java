package com.nlstn.jarvis;

import com.nlstn.jarvis.modules.WorkerModule;
import com.nlstn.jarvis.modules.command.CommandModule;
import com.nlstn.jarvis.modules.job.JobModule;
import com.nlstn.jarvis.modules.logging.Logger;
import com.nlstn.jarvis.modules.logging.LoggingModule;
import com.nlstn.jarvis.modules.settings.SettingsModule;

public class ModuleHandler {

	private static LoggingModule loggingModule;
	private static CommandModule commandModule;
	private static WorkerModule workerModule;
	private static SettingsModule settingsModule;
	private static JobModule jobModule;

	static void init() {
		Logger.info("Initializing modules");
		loggingModule = new LoggingModule();
		settingsModule = new SettingsModule();
		commandModule = new CommandModule();
		workerModule = new WorkerModule();
		jobModule = new JobModule();

		// Preinit
		Logger.trace("Preinitializing Logging Module");
		loggingModule.preInit();

		Logger.trace("Preinitializing Worker Module");
		workerModule.preInit();

		Logger.trace("Preinitializing Settings Module");
		settingsModule.preInit();

		Logger.trace("Preinitializing Command Module");
		commandModule.preInit();

		Logger.trace("Preinitializing Job Module");
		jobModule.preInit();

		// Init
		Logger.trace("Initializing Logging Module");
		commandModule.init();

		Logger.trace("Initializing Worker Module");
		workerModule.init();

		Logger.trace("Initializing Settings Module");
		settingsModule.init();

		Logger.trace("Initializing Command Module");
		commandModule.init();

		Logger.trace("Initializing Job Module");
		jobModule.init();

		// PostInit
		Logger.trace("Postinitializing Logging Module");
		loggingModule.postInit();

		Logger.trace("Postinitializing Worker Module");
		workerModule.postInit();

		Logger.trace("Postinitializing Settings Module");
		settingsModule.postInit();

		Logger.trace("Postinitializing Command Module");
		commandModule.postInit();

		Logger.trace("Postinitializing Job Module");
		jobModule.postInit();

		Logger.info("Finished initializing modules");
	}

	static void shutdown() {
		Logger.info("Shutting down modules");

		Logger.trace("Shutting down Command Module");
		commandModule.shutdown();

		Logger.trace("Shutting down Job Module");
		jobModule.shutdown();

		Logger.trace("Shutting down Settings Module");
		settingsModule.shutdown();

		Logger.trace("Shutting down Worker Module");
		workerModule.shutdown();

		Logger.trace("Shutting down Logging Module");
		loggingModule.shutdown();

		Logger.info("Finished shutting down modules");
	}

	public static LoggingModule getLoggingModule() {
		return loggingModule;
	}

	public static CommandModule getCommandModule() {
		return commandModule;
	}

	public static WorkerModule getWorkerModule() {
		return workerModule;
	}

	public static SettingsModule getSettingsModule() {
		return settingsModule;
	}

}
