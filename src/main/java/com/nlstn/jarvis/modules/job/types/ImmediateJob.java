package com.nlstn.jarvis.modules.job.types;

import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.job.Job;
import com.nlstn.jarvis.modules.job.JobExecutionPlan;
import com.nlstn.jarvis.modules.job.JobStatus;

public class ImmediateJob extends Job {

    public ImmediateJob(String name, Command command) {
        super(name, JobExecutionPlan.IMMEDITALY, command);
        setStatus(JobStatus.PLANNED);
    }

    @Override
    public boolean isReady() {
        return true;
    }

}