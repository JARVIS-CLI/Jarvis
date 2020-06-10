package com.jarvis.module.modules.job.events;

import com.jarvis.events.JarvisEvent;
import com.jarvis.module.modules.job.Job;

public abstract class JobEvent extends JarvisEvent {

    private Job job;

    public JobEvent(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

}