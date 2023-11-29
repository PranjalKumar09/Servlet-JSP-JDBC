package com.e_notes.reposistory;


import com.e_notes.enitity.Notes;
import com.e_notes.enitity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesReposistory extends JpaRepository<Notes, Integer> {
    Page<Notes> findByUser(User user, Pageable pageable);
}
