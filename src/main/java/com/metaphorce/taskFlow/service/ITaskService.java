package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.Task;
import com.metaphorce.taskFlow.model.TaskStatus;
import com.metaphorce.taskFlow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
        return taskRepository.findById(idTask).orElse(null);
    }

    @Override
    public void addNewTask(Task task){
        task.setTask_status(TaskStatus.To_do);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(long idTask) {
        Task task = getTask(idTask);
        if(null != task){
            taskRepository.delete(task);
        }
        else {
            System.out.println("Not found");
        }
    }

    @Override
    public void updateTask(long idTask, Task task) {
        Task oldTask = getTask(idTask);
        if(null != oldTask){
            task.setIdTask(oldTask.getIdTask());
            taskRepository.save(task);
        }
        else {
            System.out.println("Not found");
        }
    }

    @Override
    public void updateTaskStatus(long idTask, TaskStatus taskStatus) {
        Task task = getTask(idTask);
        if(null != task){
            task.setTask_status(taskStatus);
            if(taskStatus.equals(TaskStatus.Started)){
                task.setStart_date(LocalDate.now().toString());
            }
            if(taskStatus.equals(TaskStatus.Completed)){
                task.setEnd_date(LocalDate.now().toString());
                String [] date = task.getStart_date().split("-");
                Period elapsedTime = Period.between(LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])), LocalDate.now());
                task.setElapsed_time(elapsedTime.getDays()*24);
            }
            taskRepository.save(task);
        }
        else {
            System.out.println("Not found");
        }
    }
}
