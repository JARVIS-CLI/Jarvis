package com.nlstn.jarvis;

import com.nlstn.jarvis.modules.logging.Level;
import com.nlstn.jarvis.modules.logging.Logger;

/**
 * Main Class<br>
 * <br>
 * Creation: 26.10.2019
 *
 * @author Niklas Lahnstein
 */
public class Jarvis {

	/**
	 * The current version of {@code Jarvis}
	 */
	public static final String	VERSION	= "0.0.1";

	/**
	 * The Jarvis home directory, e.g. {@code C:/Users/firstname.lastname/Jarvis}
	 */
	public static final String	PATH	= System.getProperty("user.home") + "/Jarvis";

	/**
	 * Main entry point
	 * 
	 * @param args
	 *            currently unused
	 */
	public static void main(String[] args) {
		Logger.log(Level.INFO, "Starting Jarvis v" + VERSION);
		ModuleHandler.init();
	}

	/**
	 * Shut's down jarvis and all modules
	 */
	public static void shutdown() {
		ModuleHandler.shutdown();
		System.exit(0);
	}

}
