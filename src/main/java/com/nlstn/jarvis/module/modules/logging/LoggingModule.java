package com.nlstn.jarvis.module.modules.logging;

import com.nlstn.jarvis.module.Module;

public class LoggingModule extends Module {

    public LoggingModule() {
        super();
    }

    @Override
    public void preInit() {
        Logger.initRootLogger();
    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }
}