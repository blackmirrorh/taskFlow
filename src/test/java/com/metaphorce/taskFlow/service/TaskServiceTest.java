package com.metaphorce.taskFlow.service;

import com.metaphorce.taskFlow.model.Priority;
import com.metaphorce.taskFlow.model.Task;
import com.metaphorce.taskFlow.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        taskList.add(new Task(1L, "elDeveloper007", "Agregar boton para enviar formulario", "", "", 0, "elLider99", Priority.Low, "Started"));
        taskList.add(new Task(2L, "elDeveloper007", "Agregar boton para borrar formulario", "", "", 0, "elLider99", Priority.Medium, "Completed"));
        taskList.add(new Task(3L, "elDeveloper007", "Agregar boton para actualizar formulario", "", "", 0, "elLider99", Priority.High, "To_do"));
    }

    @Test
    public void testGetAllTasks(){
        when(taskRepository.findAll()).thenReturn(taskList);
        assertEquals(taskList.get(0).getIdTask(), taskService.getAllTasks().get(0).getIdTask());
        assertEquals(taskList.get(1).getIdTask(), taskService.getAllTasks().get(1).getIdTask());
        assertEquals(taskList.get(2).getIdTask(), taskService.getAllTasks().get(2).getIdTask());
    }

    @Test
    public void testGetTask(){
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(taskList.get(0)));
        assertEquals(taskList.get(0).getIdTask(), taskService.getTask(1L).getIdTask());
    }

    @Test
    public void testAddNewTask(){
        long id = 99L;
        Task task = new Task(id, "elDeveloper007", "Nuevo boton", "", "", 0, "elLider99", Priority.High, "To_do");
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        taskService.addNewTask(task);
        assertEquals(task.getDescription(), taskService.getTask(id).getDescription());
    }
}
