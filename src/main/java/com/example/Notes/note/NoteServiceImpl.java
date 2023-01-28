package com.example.Notes.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> findAllNotesByUserId(Integer id) {
        return noteRepository.findAllByUserId(id);
    }

    @Override
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public List<Note> findALl() {
        return noteRepository.findAll();
    }

    @Override
    public Note findById(Integer noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow();
    }

    @Override
    public void deleteNote(Integer noteId) {
         noteRepository.deleteById(noteId);
    }
}
