package com.controller;

import com.entity.Notes;
import com.entity.User;
import com.service.NotesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private NotesService notesService;

    @RequestMapping("/add_notes")
    public String add_notes() {
        return "add_notes";
    }
    @RequestMapping("/view_notes")
    public String view_notes(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Notes>  notesList = notesService.getAllNotesByUserId(user.getId());
        model.addAttribute("notesList", notesList);
        return "view_notes";
    }

    @RequestMapping("/edit_notes")
    public String edit_notes(@RequestParam("id")int id, Model model) {
        Notes notes = notesService.getNoteById(id);
        model.addAttribute("notes", notes);
        return "edit_notes";
    }

    @RequestMapping(path = "/update_notes" ,method = RequestMethod.POST )
    public String update_notes(@ModelAttribute Notes notes, HttpSession session) {
        User user = (User) session.getAttribute("user");
        notes.setUser(user);
        notes.setPublishDate(new Date());
        System.out.println(notes);
        notesService.updateNote(notes);
        session.setAttribute("msg", "Note updated successfully");
        return "redirect:/user/view_notes";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.setAttribute("msg", "Logout Successfully");
        return "login";
    }

    @RequestMapping(path = "/saveNotes", method = RequestMethod.POST)
    public String saveNotes(@ModelAttribute Notes notes, HttpSession session) {

        User user = (User) session.getAttribute("user");
        notes.setUser(user);
        notes.setPublishDate(new Date());
        notesService.insertNote(notes);

        session.setAttribute("msg", "Notes Saved");
        return "redirect:/user/add_notes";
    }

    @RequestMapping(path = "/deleteNotes")
    public String deleteNotes(@RequestParam("id") int id, HttpSession session){
        Notes notes = notesService.getNoteById(id);
        notesService.deleteNote(notes);
        session.setAttribute("msg", "Notes Deleted");
        return "redirect:/user/view_notes";
    }


}
