package com.example.backend.repositories;

import com.example.backend.entities.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>
{
	List<Note> findByCreatedBy (String createdBy);

	@Query("SELECT n FROM Note n ORDER BY n.lastModifiedDate DESC")
	List<Note> findTop3ByLastModifiedDate ();

	List<Note> findByCreatedByOrLockedFalse(String createdBy);

	List<Note> findTop3ByCreatedByOrderByLastModifiedDateDesc(String createdBy);
}
