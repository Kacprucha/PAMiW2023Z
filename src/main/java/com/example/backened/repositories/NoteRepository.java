package com.example.backened.repositories;

import com.example.backened.entities.Note;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Long> 
{
	Optional<Note> findByOwnerIgnoreCase(String username);
}
