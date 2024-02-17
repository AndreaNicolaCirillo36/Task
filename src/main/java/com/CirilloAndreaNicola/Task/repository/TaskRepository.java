package com.CirilloAndreaNicola.Task.repository;

import com.CirilloAndreaNicola.Task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDate(LocalDate date);
    List<Task> findByCompleted(Boolean taskBoolean);
}
