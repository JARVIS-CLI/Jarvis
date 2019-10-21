package com.nlstn.jarvis.logging;

public class Logger {

	private static Level currentLevel;

	static {
		currentLevel = Level.DEBUG;
	}

	public static void setLogLevel(Level level) {
		currentLevel = level;
		info("Changed Loglevel to " + level.getPrefix());
	}

	public static void log(Level level, String message) {
		if(currentLevel.getID() <= level.getID()) {
			System.out.println("[" + level.getPrefix() + "]: " + message);
		}
	}

	public static void debug(String message) {
		log(Level.DEBUG, message);
	}

	public static void trace(String message) {
		log(Level.TRACE, message);
	}

	public static void info(String message) {
		log(Level.INFO, message);
	}

	public static void warn(String message) {
		log(Level.WARNING, message);
	}

	public static void error(String message) {
		log(Level.ERROR, message);
	}

	public static void error(String message, Exception e) {
		log(Level.ERROR, message);
		e.printStackTrace(System.out);
	}

}
