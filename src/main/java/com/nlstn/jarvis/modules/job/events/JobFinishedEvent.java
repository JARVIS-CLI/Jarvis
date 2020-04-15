package com.nlstn.jarvis.modules.job.events;

import com.nlstn.jarvis.modules.job.Job;

public class JobFinishedEvent extends JobEvent {

    public JobFinishedEvent(Job job) {
        super("JobFinishedEvent", job);
    }

}