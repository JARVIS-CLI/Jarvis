package com.nlstn.jarvis.module.modules.job.events;

import com.nlstn.jarvis.module.modules.job.Job;

public class JobCreatedEvent extends JobEvent {

    public JobCreatedEvent(Job job) {
        super(job);
    }

}