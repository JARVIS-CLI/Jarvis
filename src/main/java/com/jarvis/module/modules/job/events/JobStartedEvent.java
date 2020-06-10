package com.jarvis.module.modules.job.events;

import com.jarvis.module.modules.job.Job;

public class JobStartedEvent extends JobEvent {

    public JobStartedEvent(Job job) {
        super(job);
    }

}