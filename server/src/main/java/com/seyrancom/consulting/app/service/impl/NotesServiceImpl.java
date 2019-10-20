package com.seyrancom.consulting.app.service.impl;

import com.seyrancom.consulting.app.repository.jpa.NoteRepository;
import com.seyrancom.consulting.app.repository.jpa.UserRepository;
import com.seyrancom.consulting.app.service.NotesService;
import com.seyrancom.consulting.app.domain.entity.Note;
import com.seyrancom.consulting.core.service.AbstractService;
import com.seyrancom.consulting.core.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@AppService
public class NotesServiceImpl extends AbstractService implements NotesService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Collection<Note> findAll() {
        return noteRepository.findAll();
        //return noteRepository.findByUserUsername(username);
    }

    @Override
    public void addNote(Note note) {
    /*    User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RestClientException("could not find user '" + username + "'."));
        note.setUser(user);*/
        noteRepository.save(note);
    }
}
