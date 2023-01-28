package com.example.Notes.note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {

    private String title;
    private String description;
    private Integer noteId;
    private Integer userId;

}
