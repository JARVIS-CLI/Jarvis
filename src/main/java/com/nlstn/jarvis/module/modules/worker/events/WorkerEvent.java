package com.nlstn.jarvis.module.modules.worker.events;

import com.nlstn.jarvis.events.JarvisEvent;
import com.nlstn.jarvis.module.modules.worker.Worker;

public abstract class WorkerEvent extends JarvisEvent {

    private Worker worker;

    public WorkerEvent(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }
}