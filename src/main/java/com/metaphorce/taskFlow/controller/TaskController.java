package com.metaphorce.taskFlow.controller;

import com.metaphorce.taskFlow.model.Task;
import com.metaphorce.taskFlow.model.TaskStatus;
import com.metaphorce.taskFlow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/all-tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("{idTask}")
    public Task getTasks(@PathVariable long idTask){
        return taskService.getTask(idTask);
    }

    @PostMapping("/new-task")
    public void addTask(@RequestBody @Valid Task task){
        taskService.addNewTask(task);
    }

    @DeleteMapping("{idTask}")
    public void deleteTask(@PathVariable long idTask){
        taskService.deleteTask(idTask);
    }

    @PutMapping("{idTask}")
    public void updateTask(@PathVariable long idTask, @RequestBody @Valid Task task){
        taskService.updateTask(idTask, task);
    }

    @PutMapping("/start/{idTask}")
    public void startTask(@PathVariable long idTask){
        taskService.updateTaskStatus(idTask, TaskStatus.Started);
    }

    @PutMapping("/finish/{idTask}")
    public void finishTask(@PathVariable long idTask){
        taskService.updateTaskStatus(idTask, TaskStatus.Completed);
    }
}
