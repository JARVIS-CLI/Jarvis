package com.jarvis.module.modules.settings;

public class Setting {

	private final String key;
	private String value;
	private final String defaultValue;

	public Setting(String key, String value, String defaultValue) {
		this.key = key;
		this.value = value;
		this.defaultValue = defaultValue;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void resetToDefault() {
		this.value = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

}
