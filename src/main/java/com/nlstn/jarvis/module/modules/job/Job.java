package com.nlstn.jarvis.module.modules.job;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.nlstn.jarvis.commands.Command;
import com.nlstn.jarvis.module.ModuleHandler;
import com.nlstn.jarvis.module.modules.command.events.CommandEvent;
import com.nlstn.jarvis.module.modules.command.events.CommandEventHandler;
import com.nlstn.jarvis.module.modules.command.events.CommandFailedEvent;
import com.nlstn.jarvis.module.modules.command.events.CommandFinishedEvent;
import com.nlstn.jarvis.module.modules.command.events.CommandStartedEvent;
import com.nlstn.jarvis.module.modules.job.events.JobCreatedEvent;
import com.nlstn.jarvis.module.modules.job.events.JobEvent;
import com.nlstn.jarvis.module.modules.job.events.JobEventHandler;
import com.nlstn.jarvis.module.modules.job.events.JobFailedEvent;
import com.nlstn.jarvis.module.modules.job.events.JobFinishedEvent;
import com.nlstn.jarvis.module.modules.job.events.JobStartedEvent;
import com.nlstn.jarvis.module.modules.logging.Logger;

public abstract class Job implements CommandEventHandler {

    private String id;
    private String name;
    private JobExecutionPlan executionPlan;
    private JobStatus status;
    private Command command;

    private List<JobEventHandler> eventHandlers;

    public Job(String name, JobExecutionPlan executionPlan, Command command) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.executionPlan = executionPlan;
        this.status = JobStatus.INITIAL;
        this.command = command;
        command.addEventHandler(this);
        this.eventHandlers = new ArrayList<JobEventHandler>();
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

    protected void setStatus(JobStatus status) {
        switch (status) {
            case FAILED:
                raiseEvent(new JobFailedEvent(this));
                raiseEvent(new JobFinishedEvent(this));
                break;
            case FINISHED:
                raiseEvent(new JobFinishedEvent(this));
                break;
            case INITIAL:
                raiseEvent(new JobCreatedEvent(this));
                break;
            case PLANNED:
                break;
            case RUNNING:
                raiseEvent(new JobStartedEvent(this));
                break;
            default:
                break;
        }
        this.status = status;
    }

    protected void raiseEvent(JobEvent e) {
        for (JobEventHandler handler : eventHandlers)
            handler.handleEvent(e);
    }

    public void handleEvent(CommandEvent e) {
        if (e instanceof CommandStartedEvent)
            setStatus(JobStatus.RUNNING);
        else if (e instanceof CommandFailedEvent)
            setStatus(JobStatus.FAILED);
        else if (e instanceof CommandFinishedEvent)
            setStatus(JobStatus.FINISHED);

    }
}