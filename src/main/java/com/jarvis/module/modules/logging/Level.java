package com.jarvis.module.modules.logging;

public enum Level {

    TRACE(0), DEBUG(1), INFO(2), WARNING(3), ERROR(4);

    private int intLevel;

    Level(int level) {
        this.intLevel = level;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return intLevel;
    }
}