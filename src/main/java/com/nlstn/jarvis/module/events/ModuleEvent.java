package com.nlstn.jarvis.module.events;

import com.nlstn.jarvis.events.JarvisEvent;

public abstract class ModuleEvent extends JarvisEvent {

    public ModuleEvent(String name) {
        super(name);
    }

}