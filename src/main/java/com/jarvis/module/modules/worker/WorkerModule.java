package com.jarvis.module.modules.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jarvis.Jarvis;
import com.jarvis.events.JarvisShutdownEvent;
import com.jarvis.module.Module;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.logging.Logger;

public class WorkerModule extends Module {

	private static final String STATISTICS_KEY_SUBMIT = "WorkerModule.SUBMIT_RUNNABLE";

	private ExecutorService service;

	public WorkerModule() {
		super();
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		service = Executors.newFixedThreadPool(
				Integer.parseInt(ModuleHandler.getSettingsModule().getSetting("worker.threadPool")));
		Jarvis.addEventHandler(e -> {
			if (e instanceof JarvisShutdownEvent)
				service.shutdown();
		});
	}

	@Override
	public void postInit() {

	}

	public void submitWorker(Worker worker) {
		Logger.getRootLogger().trace("Submitted new Runnable");
		if (service == null) {
			Logger.getRootLogger().error("Worker Module has not been initialized yet!");
			return;
		}
		ModuleHandler.getStatisticsModule().addRecord(STATISTICS_KEY_SUBMIT);
		service.execute(worker);
	}

	public void submitRunnable(Runnable r) {
		Worker worker = new Worker(r);
		submitWorker(worker);
	}

}
