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
		Logger.debug("Preinitializing Logging Module");
		loggingModule.preInit();

		Logger.debug("Preinitializing Worker Module");
		workerModule.preInit();

		Logger.debug("Preinitializing Settings Module");
		settingsModule.preInit();

		Logger.debug("Preinitializing Command Module");
		commandModule.preInit();

		Logger.debug("Preinitializing Job Module");
		jobModule.preInit();

		// Init
		Logger.debug("Initializing Logging Module");
		commandModule.init();

		Logger.debug("Initializing Worker Module");
		workerModule.init();

		Logger.debug("Initializing Settings Module");
		settingsModule.init();

		Logger.debug("Initializing Command Module");
		commandModule.init();

		Logger.debug("Initializing Job Module");
		jobModule.init();

		// PostInit
		Logger.debug("Postinitializing Logging Module");
		loggingModule.postInit();

		Logger.debug("Postinitializing Worker Module");
		workerModule.postInit();

		Logger.debug("Postinitializing Settings Module");
		settingsModule.postInit();

		Logger.debug("Postinitializing Command Module");
		commandModule.postInit();

		Logger.debug("Postinitializing Job Module");
		jobModule.postInit();

		Logger.info("Finished initializing modules");
	}

	static void shutdown() {
		Logger.info("Shutting down modules");

		Logger.debug("Shutting down Command Module");
		commandModule.shutdown();

		Logger.debug("Shutting down Job Module");
		jobModule.shutdown();

		Logger.debug("Shutting down Settings Module");
		settingsModule.shutdown();

		Logger.debug("Shutting down Worker Module");
		workerModule.shutdown();

		Logger.debug("Shutting down Logging Module");
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
