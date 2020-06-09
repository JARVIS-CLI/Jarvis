package com.nlstn.jarvis.module.modules.worker.events;

import com.nlstn.jarvis.module.modules.worker.Worker;

public class WorkerFailedEvent extends WorkerEvent {

    public WorkerFailedEvent(Worker worker, Exception e) {
        super(worker);
    }

}