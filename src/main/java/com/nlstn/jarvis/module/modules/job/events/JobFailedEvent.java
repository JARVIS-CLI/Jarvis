package com.nlstn.jarvis.module.modules.job.events;

import com.nlstn.jarvis.module.modules.job.Job;

public class JobFailedEvent extends JobEvent {

    public JobFailedEvent(Job job) {
        super("JobFailedEvent", job);
    }

}