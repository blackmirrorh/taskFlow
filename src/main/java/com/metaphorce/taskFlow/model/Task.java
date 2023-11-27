package com.metaphorce.taskFlow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Task {

    @Id
    @Column
    private long id_task;

    @Column
    private String username;

    @Column
    private String description;

    @Column
    private String start_date;

    @Column
    private String end_date;

    @Column
    private int elapsed_time;

    @Column
    private String assigned_by;

    @Column
    private String priority;

    @Column
    private String task_status;


    public Task() { }

    public Task(long id_task, String username, String description, String start_date, String end_date, int elapsed_time, String assigned_by, Priority priority, String task_status) {
        this.id_task = id_task;
        this.username = username;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.elapsed_time = elapsed_time;
        this.assigned_by = assigned_by;
        this.priority = priority.toString();
        this.task_status = task_status;
    }

    public long getIdTask() {
        return id_task;
    }

    public void setIdTask(long idTask) {
        this.id_task = idTask;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getElapsed_time() {
        return elapsed_time;
    }

    public void setElapsed_time(int elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public String getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(String assigned_by) {
        this.assigned_by = assigned_by;
    }

    public Priority getPriority() {
        return Priority.valueOf(priority);
    }

    public void setPriority(Priority priority) {
        this.priority = priority.toString();
    }

    public TaskStatus getTask_status() {
        return TaskStatus.valueOf(task_status);
    }

    public void setTask_status(TaskStatus task_status) {
        this.task_status = task_status.toString();
    }
}
