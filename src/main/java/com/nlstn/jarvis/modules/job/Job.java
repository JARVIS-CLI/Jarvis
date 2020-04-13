package com.nlstn.jarvis.modules.job;

import java.util.UUID;

import com.nlstn.jarvis.ModuleHandler;
import com.nlstn.jarvis.modules.command.commands.Command;
import com.nlstn.jarvis.modules.logging.Logger;

public abstract class Job {

    private String id;
    private String name;
    private JobExecutionPlan executionPlan;
    private JobStatus status;

    private Command command;

    public Job(String name, JobExecutionPlan executionPlan, Command command) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.executionPlan = executionPlan;
        this.status = JobStatus.INITIAL;
        this.command = command;
    }

    public void execute() {
        status = JobStatus.RUNNING;
        Logger.trace("Submitting job " + id);
        ModuleHandler.getWorkerModule().submitRunnable(command);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return true if the job reached the state, where it should be executed (e.g.
     *         start time reached)
     */
    public boolean isReady() {
        return false;
    }

    public JobExecutionPlan getExecutionPlan() {
        return executionPlan;
    }

    public String getName() {
        return name;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

}