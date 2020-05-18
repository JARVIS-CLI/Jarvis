package com.nlstn.jarvis.module.modules.job;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.Module;
import com.nlstn.jarvis.module.ModuleHandler;

public class JobModule extends Module {

    private JobDispatcher dispatcher;

    public JobModule() {
        super("JobModule");
    }

    public void preInit() {
        dispatcher = new JobDispatcher();
    }

    public void init() {

    }

    public void postInit() {
        ModuleHandler.getWorkerModule().submitRunnable(dispatcher);
    }

    public void executeImmediately(Command command) {
        dispatcher.dispatchImmediately(command);
    }

    public void shutdown() {
        dispatcher.shutdown();
    }
}