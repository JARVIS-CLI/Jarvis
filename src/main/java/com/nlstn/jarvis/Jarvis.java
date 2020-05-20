package com.nlstn.jarvis;

import java.util.ArrayList;
import java.util.List;

import com.nlstn.jarvis.events.JarvisEvent;
import com.nlstn.jarvis.events.JarvisEventHandler;
import com.nlstn.jarvis.events.JarvisShutdownEvent;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.logging.Level;
import com.nlstn.jarvis.module.modules.logging.Logger;

/**
 * Main Class<br>
 * <br>
 * Creation: 26.10.2019
 *
 * @author Niklas Lahnstein
 */
public class Jarvis {

	/**
	 * The current version of {@code Jarvis} cli
	 */
	public static final String VERSION = "0.0.1";

	/**
	 * The Jarvis home directory, e.g. {@code C:/Users/firstname.lastname/Jarvis}
	 */
	public static final String PATH = System.getProperty("user.home") + "/.jarvis";

	private static List<JarvisEventHandler> eventHandlers;

	/**
	 * Main entry point
	 * 
	 * @param args currently unused
	 */
	public static void main(String[] args) {
		Logger.log(Level.INFO, "Starting Jarvis v" + VERSION);
		eventHandlers = new ArrayList<JarvisEventHandler>();
		ModuleHandler.init();
	}

	/**
	 * Register a {@code JarvisEventHandler} to listen to the raised events
	 * 
	 * @param handler The event handler to register
	 */
	public static void addEventHandler(JarvisEventHandler handler) {
		eventHandlers.add(handler);
	}

	/**
	 * This method is used to dispatch a {@code JarvisEvent} to the registered event
	 * handlers.
	 * 
	 * @param e The event to raise
	 */
	private static void raiseEvent(JarvisEvent e) {
		for (JarvisEventHandler handler : eventHandlers)
			handler.handleEvent(e);
	}

	/**
	 * Shut's down jarvis and all modules
	 */
	public static void shutdown() {
		raiseEvent(new JarvisShutdownEvent());
		System.exit(0);
	}

}
