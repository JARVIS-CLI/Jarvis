package com.nlstn.jarvis.modules.job.events;

import com.nlstn.jarvis.modules.job.Job;

public class JobStartedEvent extends JobEvent {

    public JobStartedEvent(Job job) {
        super("JobStartedEvent", job);
    }

}