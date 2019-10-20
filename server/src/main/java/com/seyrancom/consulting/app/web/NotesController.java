package com.seyrancom.consulting.app.web;

import com.seyrancom.consulting.app.service.NotesService;
import com.seyrancom.consulting.app.domain.entity.Note;
import com.seyrancom.consulting.core.web.AppRestController;
import com.seyrancom.consulting.core.web.common.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@AppRestController
@RequestMapping("/mynotes")
public class NotesController extends AbstractRestController {

    @Autowired
    NotesService notesService;

    @RequestMapping("/")
    Collection<Note> readNotes() {
        return notesService.findAll();
    }

    @RequestMapping("/{username}")
    Collection<Note> readNotesByUserName(@PathVariable String username) {
        return notesService.findAll();
        //return noteRepository.findByUserUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNote(@Valid @RequestBody Note note) {
        notesService.addNote(note);
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.POST)
    public void addNoteByUserName(@PathVariable String username, @Valid @RequestBody Note note) {
        notesService.addNote(note);
    }
}
