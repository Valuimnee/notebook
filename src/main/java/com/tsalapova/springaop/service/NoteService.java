package com.tsalapova.springaop.service;

import com.tsalapova.springaop.entity.Note;

import java.util.List;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
public interface NoteService {
    List<Note> findByUserId(long id);

    Note findById(long noteId);

    Note update(Note note);

    void add(Note note);

    void delete(Long noteId);
}
