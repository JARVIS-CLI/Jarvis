package com.jarvis.module.modules.worker;

import java.util.ArrayList;
import java.util.List;

import com.jarvis.module.modules.logging.Logger;
import com.jarvis.module.modules.worker.events.WorkerEvent;
import com.jarvis.module.modules.worker.events.WorkerEventHandler;
import com.jarvis.module.modules.worker.events.WorkerFailedEvent;
import com.jarvis.module.modules.worker.events.WorkerFinishedEvent;
import com.jarvis.module.modules.worker.events.WorkerStartedEvent;

public final class Worker implements Runnable {

    private Runnable runnable;
    private List<WorkerEventHandler> eventHandlers;

    public Worker(Runnable runnable) {
        this.runnable = runnable;
        eventHandlers = new ArrayList<>();
    }

    public void run() {
        raiseEvent(new WorkerStartedEvent(this));
        try {
            runnable.run();
            raiseEvent(new WorkerFinishedEvent(this));
        } catch (Exception e) {
            raiseEvent(new WorkerFailedEvent(this, e));
            Logger.getRootLogger().error("Worker failed to execute it's task", e);
        }
    }

    private void raiseEvent(WorkerEvent e) {
        for (WorkerEventHandler handler : eventHandlers)
            handler.handleEvent(e);
    }

    public void addEventHandler(WorkerEventHandler handler) {
        eventHandlers.add(handler);
    }

}