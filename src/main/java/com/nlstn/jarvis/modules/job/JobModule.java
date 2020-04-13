package com.nlstn.jarvis.modules.job;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.Module;
import com.nlstn.jarvis.modules.command.commands.Command;

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