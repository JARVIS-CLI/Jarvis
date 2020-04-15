package com.nlstn.jarvis.module.modules.job.events;

import com.nlstn.jarvis.module.modules.job.Job;

public class JobStartedEvent extends JobEvent {

    public JobStartedEvent(Job job) {
        super("JobStartedEvent", job);
    }

}