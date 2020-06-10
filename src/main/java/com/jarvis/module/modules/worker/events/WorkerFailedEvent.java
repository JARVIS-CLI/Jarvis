package com.jarvis.module.modules.worker.events;

import com.jarvis.module.modules.worker.Worker;

public class WorkerFailedEvent extends WorkerEvent {

    private Exception cause;

    public WorkerFailedEvent(Worker worker, Exception e) {
        super(worker);
        cause = e;
    }

    public Exception getCause() {
        return cause;
    }

}