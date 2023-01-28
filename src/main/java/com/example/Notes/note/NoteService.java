package com.example.Notes.note;

import java.util.List;

public interface NoteService {
    List<Note> findAllNotesByUserId(Integer id);
    Note saveNote(Note note);
    List<Note> findALl();

    Note findById(Integer noteId);

    void deleteNote(Integer noteId);
}
