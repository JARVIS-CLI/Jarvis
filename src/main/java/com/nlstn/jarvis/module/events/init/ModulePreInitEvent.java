package com.nlstn.jarvis.module.events.init;

import com.nlstn.jarvis.module.events.ModuleEvent;

public class ModulePreInitEvent extends ModuleEvent {

    public ModulePreInitEvent(String name) {
        super("ModulePreInitEvent");
    }

}