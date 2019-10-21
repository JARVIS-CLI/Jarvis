package com.nlstn.jarvis.modules;

public abstract class Module {

	private String name;

	public Module(String name) {
		this.name = name;
	}

	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();
	
	public abstract void shutdown();

	public String getName() {
		return name;
	}
}
