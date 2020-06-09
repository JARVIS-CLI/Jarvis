package com.nlstn.jarvis.module.modules.worker.events;

import com.nlstn.jarvis.module.modules.worker.Worker;

public class WorkerFinishedEvent extends WorkerEvent {

    public WorkerFinishedEvent(Worker worker) {
        super(worker);
    }

}