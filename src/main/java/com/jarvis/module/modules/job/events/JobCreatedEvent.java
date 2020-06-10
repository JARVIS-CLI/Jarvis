package com.jarvis.module.modules.job.events;

import com.jarvis.module.modules.job.Job;

public class JobCreatedEvent extends JobEvent {

    public JobCreatedEvent(Job job) {
        super(job);
    }

}