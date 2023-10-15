package com.example.backened.repositories.impl;

import com.example.backened.entities.Note;
import com.example.backened.repositories.NoteRepository;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.stereotype.Component;

@Component
public class NoteRepositoryImpl implements NoteRepository 
{

  private final Map<Long, Note> notes = new TreeMap<>();
  private Long maxId = 0L;

  @Override
  public Collection<Note> findAll () 
  {
    return notes.values ();
  }

  @Override
  public Note save (Note note) 
  {
    if (note.getId() == null) 
    {
      note.setId(maxId + 1);
    }

    maxId = Math.max (maxId, note.getId());

    notes.put (note.getId (), note);
    return note;
  }

  @Override
  public Optional<Note> findById (Long id) 
  {
    return Optional.ofNullable (notes.get(id));
  }

  @Override
  public Optional<Note> findByOwner (String onwer) {
    Note result = null;

    for (Note value : notes.values()) 
    {
        if (Objects.equals (value.getOwner(), onwer))
            result = value;
    }

    return Optional.ofNullable (result);
  }

  @Override
  public void deleteById (Long id) 
  {
    notes.remove(id);
  }
}
