package com.seyrancom.consulting.app.service;

import com.seyrancom.consulting.app.domain.entity.Note;

import java.util.Collection;

/**
 * Created by admin on 7/7/2016.
 */
public interface NotesService {
    Collection<Note> findAll();

    void addNote(Note note);
}
