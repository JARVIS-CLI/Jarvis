package com.nlstn.jarvis.modules.job;

import java.util.ArrayList;
import java.util.List;

import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.job.types.ImmediateJob;
import com.nlstn.jarvis.modules.logging.Logger;

class JobDispatcher implements Runnable {

    private boolean running = true;
    private List<Job> activeJobs = new ArrayList<Job>();
    private List<Job> finishedJobs = new ArrayList<Job>();

    public void run() {
        while (running) {
            for (Job job : activeJobs) {
                Logger.trace("Job " + job.getId() + ", state: " + job.getStatus().toString());
                if (job.getStatus() == JobStatus.PLANNED && job.isReady()) {
                    job.execute();
                }
                if (job.getStatus() == JobStatus.FINISHED) {
                    activeJobs.remove(job);
                    finishedJobs.add(job);
                    Logger.trace("Job " + job.getId() + " found to be finished, moving to finishedJobs");
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

}