package com.example.Notes.note;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note,Integer> {

    List<Note> findAllByUserId(Integer userId);
}
