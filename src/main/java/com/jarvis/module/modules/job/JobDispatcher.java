package com.jarvis.module.modules.job;

import java.util.ArrayList;
import java.util.List;

import com.jarvis.commands.Command;
import com.jarvis.module.ModuleHandler;
import com.jarvis.module.modules.job.events.JobEvent;
import com.jarvis.module.modules.job.events.JobEventHandler;
import com.jarvis.module.modules.job.events.JobFailedEvent;
import com.jarvis.module.modules.job.events.JobFinishedEvent;
import com.jarvis.module.modules.job.types.ImmediateJob;
import com.jarvis.module.modules.logging.Logger;

class JobDispatcher implements Runnable, JobEventHandler {

    private static final String STATISTICS_KEY_EXECUTE = "JobModule.EXECUTE_JOB";

    private boolean running = true;
    private List<Job> activeJobs;

    public JobDispatcher() {
        running = true;
        activeJobs = new ArrayList<>();
    }

    public void run() {
        while (running) {
            for (Job job : activeJobs) {
                Logger.getRootLogger().trace("Job " + job.getId() + ", state: " + job.getStatus().toString());
                if (job.getStatus() == JobStatus.PLANNED && job.isReady()) {
                    job.execute();
                    ModuleHandler.getStatisticsModule().addRecord(STATISTICS_KEY_EXECUTE);
                }
            }
        }
    }

    public void shutdown() {
        running = false;
    }

    public void dispatch(Job job) {
        activeJobs.add(job);
    }

    public void dispatchImmediately(Command command) {
        ImmediateJob job = new ImmediateJob("Job", command);
        dispatch(job);
    }

    @Override
    public void handleEvent(JobEvent e) {
        if (e instanceof JobFinishedEvent) {
            activeJobs.remove(e.getJob());
        } else if (e instanceof JobFailedEvent) {
            Logger.getRootLogger().info("Job " + e.getJob().getId() + " failed!");
        }
    }

}