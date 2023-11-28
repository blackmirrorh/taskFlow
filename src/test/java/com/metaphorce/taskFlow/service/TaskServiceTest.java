package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.Priority;
import com.metaphorce.taskFlow.model.Task;
import com.metaphorce.taskFlow.model.TaskStatus;
import com.metaphorce.taskFlow.repository.TaskRepository;
import com.metaphorce.taskFlow.util.AppUserNotFoundException;
import com.metaphorce.taskFlow.util.TaskNotFoundException;
import com.metaphorce.taskFlow.util.TaskUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService = new ITaskService();

    List<Task> taskList = new ArrayList<>();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        taskList.add(new Task(1L, "elDeveloper007", "Agregar boton para enviar formulario", "", "", 0, "elLider99", Priority.Low, TaskStatus.Started.toString()));
        taskList.add(new Task(2L, "elDeveloper007", "Agregar boton para borrar formulario", "", "", 0, "elLider99", Priority.Medium, TaskStatus.Completed.toString()));
        taskList.add(new Task(3L, "elDeveloper007", "Agregar boton para actualizar formulario", "", "", 0, "elLider99", Priority.High, TaskStatus.To_do.toString()));
    }

    @Test
    public void testGetAllTasks(){
        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> taskListFromService = taskService.getAllTasks();
        assertEquals(taskList.get(0).getIdTask(), taskListFromService.get(0).getIdTask());
        assertEquals(taskList.get(1).getIdTask(), taskListFromService.get(1).getIdTask());
        assertEquals(taskList.get(2).getIdTask(), taskListFromService.get(2).getIdTask());
    }

    @Test
    public void testGetTask(){
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(taskList.get(0)));
        Task task = taskService.getTask(1L);
        assertEquals(taskList.get(0).getIdTask(), task.getIdTask());
        assertEquals(taskList.get(0).getTask_status(), task.getTask_status());
        assertEquals(taskList.get(0).getDescription(), task.getDescription());
        assertEquals(taskList.get(0).getAssigned_by(), task.getAssigned_by());
        assertEquals(taskList.get(0).getPriority(), task.getPriority());
        assertEquals(taskList.get(0).getStart_date(), task.getStart_date());
        assertEquals(taskList.get(0).getEnd_date(), task.getEnd_date());
        assertEquals(taskList.get(0).getElapsed_time(), task.getElapsed_time());
        assertEquals(taskList.get(0).getUsername(), task.getUsername());
    }

    @Test
    public void testGetTaskNotFound(){
        long id = 1L;
        when(taskRepository.findById(id)).thenThrow(new TaskNotFoundException(id));
        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(id));
    }

    @Test
    public void testAddNewTask(){
        long id = 99L;
        Task task = new Task();
        task.setIdTask(id);
        task.setUsername("elDeveloper007");
        task.setDescription("Nuevo boton");
        task.setStart_date("2023-11-11");
        task.setEnd_date("2023-11-12");
        task.setElapsed_time(24);
        task.setAssigned_by("elLider99");
        task.setPriority(Priority.High);
        task.setTask_status(TaskStatus.To_do);
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        taskService.addNewTask(task);
        assertEquals(task.getDescription(), taskService.getTask(id).getDescription());
    }

    @Test
    public void testUpdateTask(){
        long id = 99L;
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", "", "", 0, "elLider99", Priority.High, TaskStatus.To_do.toString());
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        taskService.updateTask(id, task);
        assertEquals(task.getDescription(), taskService.getTask(id).getDescription());
    }

    @Test
    public void testUpdateTaskNotFound(){
        long id = 99L;
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", "", "", 0, "elLider99", Priority.High, TaskStatus.To_do.toString());
        when(taskRepository.findById(id)).thenThrow(new TaskNotFoundException(id));
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(id, task));
    }

    @Test
    public void testDeleteTask(){
        long id = 99L;
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", "", "", 0, "elLider99", Priority.High, TaskStatus.To_do.toString());
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        taskService.deleteTask(id);
        when(taskRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(id));
    }

    @Test
    public void testUpdateTaskStatusStarted(){
        long id = 99L;
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", "", "", 0, "elLider99", Priority.High, TaskStatus.To_do.toString());
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        taskService.updateTaskStatus(id, TaskStatus.Started);
        String localDate = LocalDate.now().toString();
        assertEquals(localDate, taskService.getTask(id).getStart_date());
    }

    @Test
    public void testUpdateTaskStatusNotFound(){
        long id = 99L;
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", "", "", 0, "elLider99", Priority.High, TaskStatus.To_do.toString());
        when(taskRepository.findById(id)).thenThrow(new TaskNotFoundException(id));
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTaskStatus(id, TaskStatus.Started));
    }

    @Test
    public void testUpdateTaskStatusCompleted(){
        long id = 99L;
        String startDate = "2023-11-25";
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", startDate, "", 0, "elLider99", Priority.High, TaskStatus.Started.toString());
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        taskService.updateTaskStatus(id, TaskStatus.Completed);
        String localDate = LocalDate.now().toString();
        assertEquals(localDate, taskService.getTask(id).getEnd_date());
        assertEquals(TaskUtil.getElapsedTimeBetweenDates(startDate, localDate), taskService.getTask(id).getElapsed_time());
    }
}
