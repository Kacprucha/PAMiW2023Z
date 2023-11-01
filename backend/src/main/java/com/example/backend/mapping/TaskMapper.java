package com.example.backend.mapping;

import com.example.backend.dto.TaskDto;
import com.example.backend.entities.Task;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper 
{
    TaskDto toDto (Task task);

    Task toEntity(TaskDto dto);
}
