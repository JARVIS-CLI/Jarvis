package com.nlstn.jarvis.events;

public abstract class JarvisEvent {

    private String name;

    public JarvisEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}