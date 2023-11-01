package com.example.backend.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.dto.TaskDto;
import com.example.backend.entities.Task;
import com.example.backend.mapping.TaskMapper;
import com.example.backend.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService 
{
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepo;

    public Collection<TaskDto> findAll() 
    {
        return taskRepo.findAll().stream()
        .map(taskMapper::toDto)
        .toList();
    }

    public TaskDto findById(Long id)
    {
        return taskRepo.findById(id)
        .map(taskMapper::toDto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    }

    public Collection<TaskDto> findByCreatedBy (String owner) 
    {
        return taskRepo.findByCreatedBy(owner).stream()
        .map(taskMapper::toDto)
        .toList();
    }

    public TaskDto create(TaskDto note) 
    {
        if (note.getId () != null) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide note without id");
        }

        return save (note);
    }

    public TaskDto save (TaskDto note) 
    {
        Task entity = taskMapper.toEntity(note);
        entity = taskRepo.saveAndFlush(entity);
        return taskMapper.toDto(entity);
    }

    public TaskDto update (Long id, TaskDto note) 
    {
        if (!Objects.equals(id, note.getId())) 
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and note.id is not equal");
        }

        return save(note);
    }

    public void deleteById(Long id) 
    {
        taskRepo.deleteById(id);
    }
}
