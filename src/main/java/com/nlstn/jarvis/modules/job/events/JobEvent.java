package com.nlstn.jarvis.modules.job.events;

import com.nlstn.jarvis.JarvisEvent;
import com.nlstn.jarvis.modules.job.Job;

public abstract class JobEvent extends JarvisEvent {

    private Job job;

    public JobEvent(String name, Job job) {
        super(name);
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

}