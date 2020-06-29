package com.jarvis.module.modules.command;

import com.jarvis.module.modules.logging.Logger;

/**
 * The command domain is used to group commands. For example, most modules will
 * have a command domain, so that every command for this module is accessible
 * under this domain.
 * 
 */
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