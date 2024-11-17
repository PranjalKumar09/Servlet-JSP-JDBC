package com.service;

import com.entity.Notes;

import java.util.List;

public interface NotesService {
    public int insertNote(Notes notes);
    public Notes getNoteById(int id );
    public int updateNote(Notes notes);
    public int deleteNote(Notes  notes);
    public List<Notes> getAllNotesByUserId(int userId);
}
