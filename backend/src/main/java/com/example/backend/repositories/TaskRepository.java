package com.example.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> 
{
    List<Task> findByCreatedBy (String createdBy);

    @Query("SELECT n FROM Task n ORDER BY n.lastModifiedDate DESC")
	List<Task> findTop3ByLastModifiedDate ();
}
