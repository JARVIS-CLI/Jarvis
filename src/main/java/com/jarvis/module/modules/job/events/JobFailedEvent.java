package com.jarvis.module.modules.job.events;

import com.jarvis.module.modules.job.Job;

public class JobFailedEvent extends JobEvent {

    public JobFailedEvent(Job job) {
        super(job);
    }

}