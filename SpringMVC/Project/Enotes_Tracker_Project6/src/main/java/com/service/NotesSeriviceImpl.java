package com.service;

import com.dao.NotesDao;
import com.entity.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesSeriviceImpl implements NotesService {
    @Autowired
    private NotesDao notesDao;

    @Override
    public int deleteNote(Notes  notes) {
        return notesDao.deleteNote(notes);
    }

    @Override
    public int insertNote(Notes notes) {
        return notesDao.insertNote(notes);
    }

    @Override
    public Notes getNoteById(int id) {
        return notesDao.getNoteById(id);
    }

    @Override
    public int updateNote(Notes notes) {
        return notesDao.updateNote(notes);
    }

    @Override
    public List<Notes> getAllNotesByUserId(int userId) {
        return notesDao.getAllNotesByUserId(userId);
    }
}
