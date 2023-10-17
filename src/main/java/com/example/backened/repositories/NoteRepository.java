package com.example.backened.repositories;

import com.example.backened.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> 
{
  Note findByOwnerIgnoreCase(String username);
}
