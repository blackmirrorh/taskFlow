package com.metaphorce.taskFlow.util;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(long id){
        super(String.format("The task with Id %d was not found", id));
    }
}
