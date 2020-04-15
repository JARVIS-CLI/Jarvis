package com.nlstn.jarvis.module;

import java.util.ArrayList;
import java.util.List;

import com.nlstn.jarvis.module.events.ModuleEvent;
import com.nlstn.jarvis.module.events.ModuleEventHandler;
import com.nlstn.jarvis.module.events.ModuleShutdownEvent;

public abstract class Module {

	private String name;
	private List<ModuleEventHandler> eventHandlers;

	public Module(String name) {
		this.name = name;
		this.eventHandlers = new ArrayList<ModuleEventHandler>();
	}

	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();

	void shutdownInt() {
		raiseEvent(new ModuleShutdownEvent());
	}

	public abstract void shutdown();

	public String getName() {
		return name;
	}

	protected void raiseEvent(ModuleEvent e) {
		for (ModuleEventHandler handler : eventHandlers)
			handler.handleEvent(e);
	}

	public void addEventHandler(ModuleEventHandler handler) {
		eventHandlers.add(handler);
	}
}
