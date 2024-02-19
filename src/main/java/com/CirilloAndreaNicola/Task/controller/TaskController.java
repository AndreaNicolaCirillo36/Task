package com.CirilloAndreaNicola.Task.controller;

import com.CirilloAndreaNicola.Task.Entity.Task;
import com.CirilloAndreaNicola.Task.exception.TaskNotFoundException;
import com.CirilloAndreaNicola.Task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId){
        Task task = taskService.findById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Task>> getTaskByDate(@Valid @PathVariable LocalDate date){
        List<Task> tasks = taskService.findByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("/completed/{isCompleted}")
    public ResponseEntity<List<Task>> getTaskByCompleted(@PathVariable Boolean isCompleted){
        List<Task> tasks = taskService.findByCompleted(isCompleted);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping("/newTask")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
        Task newTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);

    }

    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @Valid @RequestBody Task task){
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

    }

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long taskId) {
        try {
            taskService.deleteTaskById(taskId);
            return ResponseEntity.ok("Task eliminata con successo");
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore interno del server");
    }
}
