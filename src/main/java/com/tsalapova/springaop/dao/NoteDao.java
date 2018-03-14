package com.tsalapova.springaop.dao;

import com.tsalapova.springaop.entity.Note;

import java.util.List;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
public interface NoteDao {
    List<Note> findByUserId(long id);

    Note findById(long noteId);

    Note updateNote(Note note);

    Note add(Note note);

    void delete(Long noteId);
}
