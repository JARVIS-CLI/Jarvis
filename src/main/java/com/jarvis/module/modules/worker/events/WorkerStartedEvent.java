package com.jarvis.module.modules.worker.events;

import com.jarvis.module.modules.worker.Worker;

public class WorkerStartedEvent extends WorkerEvent {

    public WorkerStartedEvent(Worker worker) {
        super(worker);
    }

}