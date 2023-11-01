package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> 
{
    List<Task> findByCreatedBy (String createdBy);
}
