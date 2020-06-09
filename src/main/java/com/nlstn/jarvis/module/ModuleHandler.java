package com.nlstn.jarvis.module;

import com.nlstn.jarvis.module.modules.command.CommandModule;
import com.nlstn.jarvis.module.modules.job.JobModule;
import com.nlstn.jarvis.module.modules.logging.Logger;
import com.nlstn.jarvis.module.modules.logging.LoggingModule;
import com.nlstn.jarvis.module.modules.media.MediaModule;
import com.nlstn.jarvis.module.modules.settings.SettingsModule;
import com.nlstn.jarvis.module.modules.statistics.StatisticsModule;
import com.nlstn.jarvis.module.modules.worker.WorkerModule;

public final class ModuleHandler {

	private static LoggingModule loggingModule;
	private static CommandModule commandModule;
	private static WorkerModule workerModule;
	private static SettingsModule settingsModule;
	private static JobModule jobModule;
	private static StatisticsModule statisticsModule;
	private static MediaModule mediaModule;

	private ModuleHandler() {
	}

	public static void init() {
		Logger.info("Initializing modules");
		loggingModule = new LoggingModule();
		settingsModule = new SettingsModule();
		commandModule = new CommandModule();
		workerModule = new WorkerModule();
		jobModule = new JobModule();
		statisticsModule = new StatisticsModule();
		mediaModule = new MediaModule();

		// Preinit
		Logger.debug("Preinitializing Statistics Module");
		statisticsModule.preInit();

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

		Logger.debug("Preinitializing Media Module");
		mediaModule.preInit();

		// Init
		Logger.debug("Initializing Statistics Module");
		statisticsModule.init();

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

		Logger.debug("Initializing Media Module");
		mediaModule.init();

		// PostInit
		Logger.debug("Postinitializing Statistics Module");
		statisticsModule.postInit();

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

		Logger.debug("Postinitializing Media Module");
		mediaModule.postInit();

		Logger.info("Finished initializing modules");
	}

	public static LoggingModule getLoggingModule() {
		return loggingModule;
	}

	public static StatisticsModule getStatisticsModule() {
		return statisticsModule;
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

	public static JobModule getJobModule() {
		return jobModule;
	}

	public static MediaModule getMediaModule() {
		return mediaModule;
	}

}
