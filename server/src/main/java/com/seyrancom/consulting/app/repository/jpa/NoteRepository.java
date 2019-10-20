package com.seyrancom.consulting.app.repository.jpa;

import com.seyrancom.consulting.app.domain.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
//@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository extends JpaRepository<Note, Long> {
    //Collection<Note> findByUserUsername(String username);

}
