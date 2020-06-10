package com.jarvis.module.modules.command;

import com.jarvis.module.modules.logging.Logger;

public enum CommandDomain {
    JARVIS, LOGGING, SETTINGS, JOBS, STATISTICS, COMMANDS;

    public static CommandDomain getByString(String domainString) {
        for (CommandDomain domain : values()) {
            if (domain.toString().equalsIgnoreCase(domainString))
                return domain;
        }
        Logger.getRootLogger().debug("Unknown domain requested by string: " + domainString);
        return null;
    }
}