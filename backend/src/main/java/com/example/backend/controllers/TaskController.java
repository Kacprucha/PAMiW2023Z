package com.example.backend.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.NoteDto;
import com.example.backend.dto.TaskDto;
import com.example.backend.dto.Views;
import com.example.backend.services.TaskService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Tasks", description = "Tasks management APIs")
@RequestMapping(value = "tasks")
@RequiredArgsConstructor
public class TaskController 
{
    private final TaskService taskService;

    @Operation(
      summary = "Create a Task",
      description = "Create a Task object.",
      tags = {"post"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = Views.Get.class)
    public TaskDto create (@RequestBody @JsonView(value = Views.Post.class) TaskDto task) 
    {
        log.debug ("Create task: {}", task);
        return taskService.create (task);
    }

    @Operation(
      summary = "Find all Tasks",
      description = "Get all Tasks objects.",
      tags = {"get"})
    @GetMapping
    @JsonView(Views.Get.class)
    public Collection<TaskDto> findAll()
    {
        log.debug ("Find all tasks");
        return taskService.findAll ();
    }

    @Operation(
        summary = "Find a Task by Id",
        description = "Get a Task object by specifying its id.",
        tags = {"get"})
    @ApiResponse(
        responseCode = "200",
        content = {
            @Content(schema = @Schema(implementation = TaskDto.class), mediaType = "application/json")})
    @ApiResponse(
        responseCode = "404",
        content = {@Content(schema = @Schema())})
    @ApiResponse(
        responseCode = "500",
        content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @JsonView(value = Views.Get.class)
    public TaskDto findById (
        @Parameter(description = "Task Id.", example = "1")
        @PathVariable Long id)
    {
        log.debug ("Find task with id: {}", id);
        return taskService.findById (id);
    }

    @Operation(
        summary = "Find 3 last updated Tasks",
        description = "Get 3 Tasks that was last updated.",
        tags = {"get"})
    @GetMapping("/last-updated")
    @JsonView(Views.Get.class)
    public Collection<TaskDto> getTop3ByUpdateDate()
    {
        log.debug ("Find 3 last updated tasks");
        return taskService.findTop3ByUpdateDate();
    }

    @Operation(
        summary = "Update a Task by Id",
        description = "Update a Task object by specifying its id.",
        tags = {"put"})
    @PutMapping("/{id}")
    @JsonView(value = Views.Get.class)
    public TaskDto update (
        @Parameter(description = "Task Id.", example = "1")
        @PathVariable Long id,
        @RequestBody @JsonView(value = Views.Put.class) TaskDto task)
    {
        log.debug ("Update task with id: {}, with task {}", id, task);
        return taskService.update (id, task);
    }

    @Operation(
        summary = "Delete a Task by Id",
        description = "Delete a Task object by specifying its id.",
        tags = {"delete"})
    @DeleteMapping("/{id}")
    public void deleteTask (
        @Parameter(description = "Task Id.", example = "1")
        @PathVariable Long id) 
    {
        log.debug ("Delete task with id: {}", id);
        taskService.deleteById (id);
    }

    @Operation(
        summary = "Find all Tasks created by owner",
        description = "Get all Tasks objects created by owner.",
        tags = {"get"})
    @GetMapping("/author/{owner}")
    @JsonView(Views.Get.class)
    public Collection<TaskDto> findByCreatedBy (
        @Parameter(description = "Owner of tasks.", example = "anonymous")
        @PathVariable String owner)
    {
        log.debug ("Find tasks created by owner: {}", owner);
        return taskService.findByCreatedBy (owner);
    }
}
