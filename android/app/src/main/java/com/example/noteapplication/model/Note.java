package com.example.noteapplication.model;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Note
{
    private Long id;
    private Boolean locked;
    private String title;
    private String text;
    private String createdBy;
    private String lastModifiedDate;

}
