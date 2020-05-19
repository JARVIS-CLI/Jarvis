package com.nlstn.jarvis.module.modules.settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nlstn.jarvis.Jarvis;
import com.nlstn.jarvis.events.JarvisShutdownEvent;
import com.nlstn.jarvis.module.Module;
import com.nlstn.jarvis.module.modules.logging.Logger;

public class SettingsModule extends Module {

	private Settings settings;
	private String settingsPath;

	public SettingsModule() {
		super();
	}

	@Override
	public void preInit() {
		settings = new Settings();
		settingsPath = Jarvis.PATH + "/settings.opt";
		File settingsFile = new File(settingsPath);
		if (!settingsFile.canRead()) {
			Logger.trace("Cannot read SettingsFile " + settingsPath);
			try {
				settingsFile.getParentFile().mkdirs();
				settingsFile.createNewFile();
			} catch (IOException e) {
				Logger.error("Failed to create settings file!", e);
			}
			// Set defaults
			settings.reset();
			return;
		}
		settings.loadFromFile(settingsPath);
	}

	@Override
	public void init() {
		Jarvis.addEventHandler((e) -> {
			if (e instanceof JarvisShutdownEvent)
				save();
		});
	}

	@Override
	public void postInit() {

	}

	public boolean settingExists(String key) {
		return settings.settingExists(key);
	}

	public void save() {
		settings.storeToFile(settingsPath);
	}

	public String getSetting(String key) {
		Optional<Setting> optSetting = settings.getSetting(key);
		if (optSetting.isPresent())
			return optSetting.get().getValue();
		else
			return null;
	}

	public List<Setting> getSettings() {
		return new ArrayList<Setting>(settings.settings);
	}

	public void setSetting(String key, String value) {
		settings.setSetting(key, value);
	}

	public void reset() {
		settings.reset();
	}

	public boolean reset(String key) {
		return settings.reset(key);
	}

	public void reload() {
		settings.loadFromFile(settingsPath);
	}

	public void reload(String key) {
		settings.loadFromFile(settingsPath, key);
	}

}