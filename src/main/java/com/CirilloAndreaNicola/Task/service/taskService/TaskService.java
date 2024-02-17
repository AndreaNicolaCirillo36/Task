package com.CirilloAndreaNicola.Task.service.taskService;

import com.CirilloAndreaNicola.Task.Entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    List<Task> findAll();
    Task findById(Long taskId);
    List<Task> findByDate(LocalDate date);
    List<Task> findByCompleted(Boolean taskBoolean);
    Task saveTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTaskById(Long taskId);
}
