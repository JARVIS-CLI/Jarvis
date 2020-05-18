package com.nlstn.jarvis.module.modules;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nlstn.jarvis.Jarvis;
import com.nlstn.jarvis.events.JarvisShutdownEvent;
import com.nlstn.jarvis.module.Module;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class WorkerModule extends Module {

	private static final String STATISTICS_KEY_SUBMIT = "WorkerModule.SUBMIT_RUNNABLE";

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
		Jarvis.addEventHandler((e) -> {
			if (e instanceof JarvisShutdownEvent)
				service.shutdown();
		});
	}

	@Override
	public void postInit() {

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
