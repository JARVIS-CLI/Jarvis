package com.nlstn.jarvis.modules.logging;

public enum Level {

    TRACE(0), DEBUG(1), INFO(2), WARNING(3), ERROR(4);

    private int level;

    Level(int level) {
        this.level = level;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }
}