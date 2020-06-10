package com.jarvis.module.modules.job.types;

import com.jarvis.commands.Command;
import com.jarvis.module.modules.job.Job;
import com.jarvis.module.modules.job.JobExecutionPlan;
import com.jarvis.module.modules.job.JobStatus;

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