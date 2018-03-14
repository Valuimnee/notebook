package com.tsalapova.springaop.service.impl;

import com.tsalapova.springaop.dao.NoteDao;
import com.tsalapova.springaop.entity.Note;
import com.tsalapova.springaop.service.NoteService;
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
    @Autowired
    private NoteDao noteDao;

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
