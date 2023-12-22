package com.example.backend.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class TaskDto 
{
    @Schema(description = "Note primary key")
    @JsonView({Views.Get.class, Views.Put.class})
    Long id;

    @Schema(description = "Note text", example = "Some text")
    @JsonView({Views.Get.class, Views.Put.class, Views.Post.class})
    String text;

    @Schema(description = "Deadline date", example = "2023-11-01 17:32:06.451605+00")
    @JsonView({Views.Get.class, Views.Put.class, Views.Post.class})
    Instant deadline;

    @JsonView({Views.Get.class})
    Instant lastModifiedDate;
}
