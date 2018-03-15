package com.tsalapova.notebook.service.impl;

import com.tsalapova.notebook.dao.NoteDao;
import com.tsalapova.notebook.entity.Note;
import com.tsalapova.notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    private NoteDao noteDao;

    @Autowired
    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public List<Note> findByUserId(long id) {
        return noteDao.findByUserId(id);
    }

    @Override
    public Note findById(long noteId) {
        return noteDao.findById(noteId);
    }

    @Override
    public Note update(Note note) {
        return noteDao.updateNote(note);
    }

    @Override
    public void add(Note note) {
        noteDao.add(note);
    }

    @Override
    public void delete(Long noteId) {
        noteDao.delete(noteId);
    }
}
