package com.dao;

import com.entity.Notes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotesDao {
    @Autowired
    private SessionFactory sessionFactory;

    public int insertNote(Notes notes) {
        int notesId = 0;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(notes);
            tx.commit();
            notesId = notes.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notesId;
    }
        public Notes getNoteById(int id) {
        Notes notes = null;
        try (Session session = sessionFactory.openSession()) {
            notes = session.get(Notes.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }
    public int updateNote(Notes notes) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(notes);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    public int deleteNote(Notes  notes) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(notes);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    public List<Notes> getAllNotesByUserId(int userId) {
        List<Notes> notesList = new ArrayList<>();  // Initialize the list
        try (Session session = sessionFactory.openSession()) {
            Query<Notes> query = session.createQuery("from Notes where user.id = :userId", Notes.class);
            query.setParameter("userId", userId);
            notesList = query.list();  // Execute the query and assign the result to notesList
        } catch (Exception e) {
            e.printStackTrace();  // Print exception stack trace for debugging
        }
        return notesList;
    }

}
