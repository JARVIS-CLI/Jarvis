package com.nlstn.jarvis;

public abstract class JarvisEvent {

    private String name;

    public JarvisEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}