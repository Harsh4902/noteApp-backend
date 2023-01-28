package com.example.Notes.note;

import com.example.Notes.repository.UserRepository;
import com.example.Notes.user.Role;
import com.example.Notes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<NoteResponse> getAllNotes(){
        List<Note> notes = noteService.findALl();
        List<NoteResponse> allNotes = new ArrayList<>();

        for(Note a : notes){
            allNotes.add(NoteResponse.builder()
                            .noteId(a.getId())
                            .title(a.getTitle())
                            .description(a.getDescription())
                            .userId(a.getUser().getId())
                            .build()
            );
        }

        return allNotes;
    }

    @PostMapping("/create")
    public ResponseEntity createNote(@RequestBody NoteRequest noteRequest){

        var note = Note.builder()
                .title(noteRequest.getTitle())
                .description(noteRequest.getDescription())
                .user(  User.builder()
                        .id(noteRequest.getUserId())
                        .role(Role.USER)
                        .build())
                .build();

        Note createdNote = noteService.saveNote(note);
        NoteResponse noteResponse = NoteResponse.builder()
                .noteId(createdNote.getId())
                .title(createdNote.getTitle())
                .description(createdNote.getDescription())
                .userId(createdNote.getUser().getId())
                .build();
        return new ResponseEntity(noteResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAllnoteForUser(@PathVariable("id") Integer id){
        List<Note> notes = noteService.findAllNotesByUserId(id);
        List<NoteResponse> allNotes = new ArrayList<>();

        for(Note a : notes){
            allNotes.add(NoteResponse.builder()
                    .noteId(a.getId())
                    .title(a.getTitle())
                    .description(a.getDescription())
                    .userId(a.getUser().getId())
                    .build()
            );
        }

        return new ResponseEntity(allNotes,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNote(@RequestBody NoteRequest noteRequest,@PathVariable("id") Integer id){
        Note note = noteService.findById(id);

        note.setTitle(noteRequest.getTitle());
        note.setDescription(noteRequest.getDescription());

        Note updatedNote = noteService.saveNote(note);
        NoteResponse noteResponse = NoteResponse.builder()
                .noteId(updatedNote.getId())
                .title(updatedNote.getTitle())
                .description(updatedNote.getDescription())
                .userId(updatedNote.getUser().getId())
                .build();
        return new ResponseEntity(noteResponse,HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") Integer id){
        noteService.deleteNote(id);
        var noteResponse = NoteResponse.builder().build();
        return new ResponseEntity(noteResponse,HttpStatus.ACCEPTED);
    }

}
