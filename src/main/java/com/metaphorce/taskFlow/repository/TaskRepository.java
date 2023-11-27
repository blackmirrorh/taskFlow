package com.metaphorce.taskFlow.repository;

import com.metaphorce.taskFlow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
