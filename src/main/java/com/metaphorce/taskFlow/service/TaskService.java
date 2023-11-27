package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.Task;
import com.metaphorce.taskFlow.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTask(long idTask);

    void addNewTask(Task task);

    void deleteTask(long idTask);

    void updateTask(long idTask, Task task);

    void updateTaskStatus(long idTask, TaskStatus taskStatus);
}
