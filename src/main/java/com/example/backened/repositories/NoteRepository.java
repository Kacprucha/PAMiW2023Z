package com.example.backened.repositories;

import com.example.backened.entities.Note;
import java.util.Collection;
import java.util.Optional;

public interface NoteRepository {

  Collection<Note> findAll ();

  Note save (Note note);

  Optional<Note> findById (Long id);

  Optional<Note> findByOwner (String owner);

  void deleteById (Long id);
}
