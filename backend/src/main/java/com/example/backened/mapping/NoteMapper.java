package com.example.backened.mapping;


import com.example.backened.dto.NoteDto;
import com.example.backened.entities.Note;
import org.mapstruct.Mapper;

@Mapper
public interface NoteMapper {

  NoteDto toDto(Note note);

  Note toEntity(NoteDto dto);
}
