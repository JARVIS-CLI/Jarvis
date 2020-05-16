package com.nlstn.jarvis.module.modules.command;

import com.nlstn.jarvis.module.modules.logging.Logger;

public enum CommandDomain {
    JARVIS, LOGGING, SETTINGS, JOBS, STATISTICS;

    public static CommandDomain getByString(String domainString) {
        for (CommandDomain domain : values()) {
            if (domain.toString().equalsIgnoreCase(domainString))
                return domain;
        }
        Logger.debug("Unknown domain requested by string: " + domainString);
        return null;
    }
}