package com.nlstn.jarvis.modules;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.logging.Logger;

public class WorkerModule extends Module {

	private ExecutorService service;

	public WorkerModule() {
		super("WorkerModule");
	}

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		service = Executors.newFixedThreadPool(Integer.parseInt(ModuleHandler.getSettingsModule().getSetting("worker.threadPool")));
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
		if(service == null)
			Logger.error("Worker Module has not been initialized yet!");
		service.execute(r);
	}

}
