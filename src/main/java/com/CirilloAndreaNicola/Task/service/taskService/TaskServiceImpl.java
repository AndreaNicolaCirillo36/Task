package com.CirilloAndreaNicola.Task.service.taskService;

import com.CirilloAndreaNicola.Task.Entity.Task;
import com.CirilloAndreaNicola.Task.exception.TaskNotFoundException;
import com.CirilloAndreaNicola.Task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAll();
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("Nessuna task trovata");
        }
        return tasks;
    }

    @Override
    public Task findById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Nessuna task con id: " + taskId));

        return task;
    }

    @Override
    public List<Task> findByDate(LocalDate date) {
        List<Task> tasks = taskRepository.findByDate(date);
        if (tasks.isEmpty()){
            throw new TaskNotFoundException("Nessuna task trovata con data: " + date);
        }
        return tasks;
    }

    @Override
    public List<Task> findByCompleted(Boolean taskBoolean) {
        List<Task> tasks = taskRepository.findByCompleted(taskBoolean);
        if (tasks.isEmpty()){
            throw new TaskNotFoundException("Nessuna task trovata");
        }
        return tasks;
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task taskUpdate) {
        Task existTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Nessuna task con id: " + taskId));

        existTask.setDescription(taskUpdate.getDescription());
        existTask.setCompleted(taskUpdate.getCompleted());
        if (taskUpdate.getDate() != null) {
            existTask.setDate(taskUpdate.getDate());
        }

        return taskRepository.save(existTask);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        Task deleteTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Nessuna task con id: " + taskId));

        taskRepository.delete(deleteTask);
    }
}
