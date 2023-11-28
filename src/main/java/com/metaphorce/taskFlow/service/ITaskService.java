package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.Task;
import com.metaphorce.taskFlow.model.TaskStatus;
import com.metaphorce.taskFlow.repository.TaskRepository;
import com.metaphorce.taskFlow.util.TaskNotFoundException;
import com.metaphorce.taskFlow.util.TaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ITaskService implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(long idTask){
        return taskRepository.findById(idTask).orElseThrow(() -> new TaskNotFoundException(idTask));
    }

    @Override
    public void addNewTask(Task task){
        task.setTask_status(TaskStatus.To_do);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(long idTask) {
        Task task = getTask(idTask);
        taskRepository.delete(task);
    }

    @Override
    public void updateTask(long idTask, Task task) {
        Task oldTask = getTask(idTask);
        task.setIdTask(oldTask.getIdTask());
        taskRepository.save(task);
    }

    @Override
    public void updateTaskStatus(long idTask, TaskStatus taskStatus) {
        Task task = getTask(idTask);
        task.setTask_status(taskStatus);
        switch (taskStatus){
            case Started:
                task.setStart_date(LocalDate.now().toString());
                break;
            case Completed:
                String endDate = LocalDate.now().toString();
                task.setEnd_date(endDate);
                int elapsedDays = TaskUtil.getElapsedTimeBetweenDates(task.getStart_date(), endDate);
                task.setElapsed_time(elapsedDays);
                break;
        }
        taskRepository.save(task);
    }
}
