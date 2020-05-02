package com.nlstn.jarvis.module.modules;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nlstn.jarvis.module.Module;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class WorkerModule extends Module {

	private static final String STATISTICS_KEY_SUBMIT = "WorkerModule.STATISTICS_KEY_SUBMIT";

	private ExecutorService service;

	public WorkerModule() {
		super("WorkerModule");
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		service = Executors.newFixedThreadPool(
				Integer.parseInt(ModuleHandler.getSettingsModule().getSetting("worker.threadPool")));
	}

	@Override
	public void postInit() {

	}

	@Override
	public void shutdown() {
		service.shutdown();
	}

	public void submitRunnable(Runnable r) {
		Logger.trace("Submitted new Runnable");
		if (service == null) {
			Logger.error("Worker Module has not been initialized yet!");
			return;
		}
		ModuleHandler.getStatisticsModule().addRecord(STATISTICS_KEY_SUBMIT);
		service.execute(r);
	}

}
