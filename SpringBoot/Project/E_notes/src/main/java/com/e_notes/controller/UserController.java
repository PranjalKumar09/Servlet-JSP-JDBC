package com.e_notes.controller;

import com.e_notes.enitity.Notes;
import com.e_notes.enitity.User;
import com.e_notes.reposistory.UserReposistory;
import com.e_notes.service.NotesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private NotesService notesService;
    @Autowired
    private UserReposistory userReposistory;

    @ModelAttribute
    public User getUser(Principal principal, Model model) {
        String email = principal.getName();
        User user = userReposistory.findByEmail(email);
        model.addAttribute("user", user);
        return user;
    }

    @GetMapping("/add_notes")
    public String add_notes() {
        return "add_notes";
    }

    @GetMapping("/edit_notes/{id}")
    public String edit_notes(@PathVariable int id, Model model) {
        Notes notes = notesService.getNotesById(id);
        model.addAttribute("notes", notes);
        return "edit_notes";
    }




    @PostMapping("/saveNotes")
    public String save_notes(@ModelAttribute Notes notes, HttpSession session, Principal principal, Model model) {
        notes.setDate(new Date());
        notes.setUser(getUser(principal, model));


        Notes savedNotes = notesService.saveNotes(notes);

        if (savedNotes != null) {
            session.setAttribute("msg", "Successfully Saved");
        } else {
            session.setAttribute("msg", "Something went wrong");
        }
        return "redirect:/user/add_notes";
    }

    @PostMapping("/updateNote")
    public String updateNote(@ModelAttribute Notes notes, HttpSession session, Principal principal, Model model) {
        notes.setDate(new Date());
        notes.setUser(getUser(principal, model));


        Notes savedNotes = notesService.saveNotes(notes);

        if (savedNotes != null) {
            session.setAttribute("msg", "Updated Notes");
        } else {
            session.setAttribute("msg", "Something went wrongn while updating");
        }
        return "redirect:/user/view_notes";
    }
        @GetMapping("/view_notes")
        public String view_notes(Model model, Principal principal, @RequestParam(defaultValue = "0") Integer pageNo ) {


            User user = getUser(principal, model);
            Page<Notes> notesList = notesService.getNotesByUser(user, pageNo);
            model.addAttribute("notesList", notesList);
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalElements", notesList.getTotalElements( 
            
            ));
            model.addAttribute("totalPages", notesList.getTotalPages());
            return "view_notes";
        }
    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable int id, HttpSession session) {
        boolean check = notesService.deleteNotes(id);
        if (check) {
            session.setAttribute("msg", "Delete Note Success");
        } else {
            session.setAttribute("msg", "Failed to Delete Note");
        }
        return "redirect:/user/view_notes";
    }
}
