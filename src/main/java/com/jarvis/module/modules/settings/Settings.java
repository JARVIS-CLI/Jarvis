package com.jarvis.module.modules.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jarvis.module.modules.logging.Logger;

public class Settings {

	List<Setting> settingsList;

	public Settings() {
		settingsList = new ArrayList<>();

		settingsList.add(new Setting("worker.threadPool", null, "10"));
		settingsList.add(new Setting("logger.maxQueueSize", null, "200"));
		settingsList.add(new Setting("logger.level", null, "INFO"));
		settingsList.add(new Setting("commands.maxRecentCommandsSize", null, "10"));
	}

	public boolean settingExists(String key) {
		return settingsList.parallelStream().anyMatch(setting -> setting.getKey().equals(key));
	}

	public Optional<Setting> getSetting(String key) {
		return settingsList.parallelStream().filter(setting -> setting.getKey().equals(key)).findAny();
	}

	public void setSetting(String key, final String value) {
		Optional<Setting> optSetting = getSetting(key);
		optSetting.ifPresent(setting -> setting.setValue(value));
	}

	public boolean reset(String key) {
		Optional<Setting> optSetting = getSetting(key);
		optSetting.ifPresent(Setting::resetToDefault);
		return optSetting.isPresent();
	}

	public void reset() {
		for (Setting setting : settingsList)
			setting.resetToDefault();
	}

	public void loadFromFile(String path) {
		loadFromFile(path, null);
	}

	public void loadFromFile(String path, String reloadKey) {
		File file = new File(path);
		if (!file.exists()) {
			Logger.getRootLogger().error("Settings file does not exist!");
			return;
		}
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {

			String line;

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("#"))
					continue;
				String[] split = line.split("=");
				if (split.length != 2) {
					Logger.getRootLogger().error("Malformed settings line: " + line);
					continue;
				}
				if (reloadKey != null && split[0].equals(reloadKey))
					continue;
				Optional<Setting> optSetting = getSetting(split[0]);
				optSetting.ifPresent(setting -> {
					setting.setValue(split[1]);
					Logger.getRootLogger()
							.trace("Found setting " + setting.getKey() + " with value " + setting.getValue());
				});
			}
		} catch (IOException e) {
			Logger.getRootLogger().error("Failed to load settings!", e);
		}
		for (Setting setting : settingsList) {
			if (setting.getValue() == null)
				setting.resetToDefault();
		}
	}

	public void storeToFile(String settingsPath) {
		Path path = Paths.get(settingsPath);

		StringBuilder builder = new StringBuilder();
		for (Setting setting : settingsList) {
			builder.append(setting.getKey());
			builder.append("=");
			builder.append(setting.getValue());
			builder.append("\n");
		}

		try {
			Files.write(path, builder.toString().getBytes());
		} catch (IOException e) {
			Logger.getRootLogger().error("Failed to store settings", e);
		}
	}
}
