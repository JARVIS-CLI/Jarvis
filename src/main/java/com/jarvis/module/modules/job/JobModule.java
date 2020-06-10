package com.jarvis.module.modules.job;

import com.jarvis.Jarvis;
import com.jarvis.commands.Command;
import com.jarvis.events.JarvisShutdownEvent;
import com.jarvis.module.Module;
import com.jarvis.module.ModuleHandler;

public class JobModule extends Module {

    private JobDispatcher dispatcher;

    public JobModule() {
        super();
    }

    public void preInit() {
        dispatcher = new JobDispatcher();
        Jarvis.addEventHandler(e -> {
            if (e instanceof JarvisShutdownEvent)
                dispatcher.shutdown();
        });
    }

    public void init() {

    }

    public void postInit() {
        ModuleHandler.getWorkerModule().submitRunnable(dispatcher);
    }

    public void executeImmediately(Command command) {
        dispatcher.dispatchImmediately(command);
    }
}