package com.nlstn.jarvis.modules.settings;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.nlstn.jarvis.logging.Logger;
import com.nlstn.jarvis.modules.Module;

public class SettingsModule extends Module {

	private Settings	settings;
	private String		settingsPath;

	public SettingsModule() {
		super("SettingsModule");
	}

	@Override
	public void preInit() {
		settings = new Settings();
		settingsPath = System.getProperty("user.home") + "/Jarvis/settings.opt";
		File settingsFile = new File(settingsPath);
		if(!settingsFile.canRead()) {
			Logger.trace("Cannot read SettingsFile " + settingsPath);
			try {
				settingsFile.getParentFile().mkdirs();
				settingsFile.createNewFile();
			}
			catch (IOException e) {
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

	}

	@Override
	public void postInit() {

	}

	@Override
	public void shutdown() {
		save();
	}

	public boolean settingExists(String key) {
		return settings.settingExists(key);
	}

	public void save() {
		settings.storeToFile(settingsPath);
	}

	public String getSetting(String key) {
		Optional<Setting> optSetting = settings.getSetting(key);
		if(optSetting.isPresent())
			return optSetting.get().getValue();
		else
			return null;
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