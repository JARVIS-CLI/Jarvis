package com.nlstn.jarvis.modules.job.events;

import com.nlstn.jarvis.modules.job.Job;

public class JobCreatedEvent extends JobEvent {

    public JobCreatedEvent(Job job) {
        super("JobCreatedEvent", job);
    }

}