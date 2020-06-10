package com.jarvis.events;

public abstract class JarvisEvent {

    private String name;

    public JarvisEvent() {
        this.name = this.getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

}