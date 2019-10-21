package com.nlstn.jarvis.logging;

public enum Level {

	DEBUG(0, "DEBUG"), TRACE(1, "TRACE"), INFO(2, "INFO"), WARNING(3, "WARN"), ERROR(4, "ERROR");

	private int		level;
	private String	prefix;

	Level(int id, String prefix) {
		this.level = id;
		this.prefix = prefix;
	}

	public int getID() {
		return level;
	}

	public String getPrefix() {
		return prefix;
	}

}
