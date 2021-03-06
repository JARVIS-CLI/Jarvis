package com.jarvis.module;

import java.util.ArrayList;
import java.util.List;

import com.jarvis.module.events.ModuleEvent;
import com.jarvis.module.events.ModuleEventHandler;

public abstract class Module {

	private String name;
	private List<ModuleEventHandler> eventHandlers;

	public Module() {
		name = this.getClass().getSimpleName();
		this.eventHandlers = new ArrayList<>();
	}

	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();

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
