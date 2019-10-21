package com.nlstn.jarvis;

import com.nlstn.jarvis.logging.Level;
import com.nlstn.jarvis.logging.Logger;

public class Jarvis {

	public static final String VERSION = "0.0.1";

	public static void main(String[] args) {
		Logger.log(Level.INFO, "Starting Jarvis v" + VERSION);
		ModuleHandler.init();
	}

	public static void shutdown() {
		ModuleHandler.shutdown();
		System.exit(0);
	}

}
