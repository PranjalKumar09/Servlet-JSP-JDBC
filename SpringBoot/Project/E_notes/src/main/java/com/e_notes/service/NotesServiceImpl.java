package com.e_notes.service;

import com.e_notes.enitity.Notes;
import com.e_notes.enitity.User;
import com.e_notes.reposistory.NotesReposistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    private NotesReposistory notesReposistory;


    @Override
    public boolean deleteNotes(int id) {
        Notes notes = notesReposistory.getReferenceById(id);
        if (notes != null) {
            notesReposistory.delete(notes);
            return true;
        }
        return false;
    }

    @Override
    public Notes getNotesById(int id) {
        return notesReposistory.findById(id).get();
    }

    @Override
    public Page<Notes> getNotesByUser(User user, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);

        return  notesReposistory.findByUser(user , pageable);
    }

    @Override
    public Notes saveNotes(Notes notes) {
        return notesReposistory.save(notes);
    }

    @Override
    public Notes updateNotes(Notes notes) {
        return notesReposistory.save(notes);
    }
}
