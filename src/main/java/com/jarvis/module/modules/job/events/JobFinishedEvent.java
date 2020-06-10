package com.jarvis.module.modules.job.events;

import com.jarvis.module.modules.job.Job;

public class JobFinishedEvent extends JobEvent {

    public JobFinishedEvent(Job job) {
        super(job);
    }

}