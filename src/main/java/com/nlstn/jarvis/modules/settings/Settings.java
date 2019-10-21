package com.nlstn.jarvis.modules.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nlstn.jarvis.logging.Logger;

public class Settings {

	private List<Setting> settings;

	public Settings() {
		settings = new ArrayList<Setting>();

		settings.add(new Setting("exitTimeout", null, "5000"));
		settings.add(new Setting("workerThreadPool", null, "10"));
	}

	public boolean settingExists(String key) {
		return settings.parallelStream().anyMatch(setting -> setting.getKey().equals(key));
	}

	public Optional<Setting> getSetting(String key) {
		return settings.parallelStream().filter(setting -> setting.getKey().equals(key)).findAny();
	}

	public void setSetting(String key, final String value) {
		Optional<Setting> optSetting = getSetting(key);
		optSetting.ifPresent(setting -> setting.setValue(value));
	}

	public boolean reset(String key) {
		Optional<Setting> optSetting = getSetting(key);
		optSetting.ifPresent(setting -> setting.resetToDefault());
		if(optSetting.isPresent())
			return true;
		else
			return false;
	}

	public void reset() {
		for (Setting setting : settings)
			setting.resetToDefault();
	}

	public void loadFromFile(String path) {
		loadFromFile(path, null);
	}

	public void loadFromFile(String path, String reloadKey) {
		File file = new File(path);
		if(!file.exists()) {
			Logger.error("Settings file does not exist!");
			return;
		}
		try {
			BufferedReader reader = Files.newBufferedReader(Paths.get(path));

			String line;

			while ((line = reader.readLine()) != null) {
				if(line.startsWith("#"))
					continue;
				String[] split = line.split("=");
				if(split.length != 2) {
					Logger.error("Malformed settings line: " + line);
					continue;
				}
				if(reloadKey != null && split[0].equals(reloadKey))
					continue;
				Optional<Setting> optSetting = getSetting(split[0]);
				optSetting.ifPresent(setting -> {
					setting.setValue(split[1]);
					Logger.trace("Found setting " + setting.getKey() + " with value " + setting.getValue());
				});
			}
			reader.close();
		}
		catch (IOException e) {
			Logger.error("Failed to load settings!", e);
		}
		for (Setting setting : settings) {
			if(setting.getValue() == null)
				setting.resetToDefault();
		}
	}

	public void storeToFile(String settingsPath) {
		Path path = Paths.get(settingsPath);

		StringBuilder builder = new StringBuilder();
		for (Setting setting : settings) {
			builder.append(setting.getKey());
			builder.append("=");
			builder.append(setting.getValue());
			builder.append("\n");
		}

		try {
			Files.write(path, builder.toString().getBytes());
		}
		catch (IOException e) {
			Logger.error("Failed to store settings", e);
		}
	}
}
