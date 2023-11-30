package com.e_notes.service;

import com.e_notes.enitity.Notes;
import com.e_notes.enitity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NotesService {
    Notes saveNotes(Notes notes);
    Notes getNotesById(int id);
    Page<Notes> getNotesByUser(User user, int pageNo);
    Notes updateNotes(Notes notes);
    boolean deleteNotes(int id);
}
