package com.nlstn.jarvis.modules.job.events;

import com.nlstn.jarvis.modules.job.Job;

public class JobFailedEvent extends JobEvent {

    public JobFailedEvent(Job job) {
        super("JobFailedEvent", job);
    }

}