package com.tsalapova.springaop.dao.impl;

import com.tsalapova.springaop.dao.AbstractDao;
import com.tsalapova.springaop.dao.NoteDao;
import com.tsalapova.springaop.entity.Note;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Repository
public class NoteDaoImpl extends AbstractDao<Long, Note> implements NoteDao {

    @Override
    public List<Note> findByUserId(long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("userId", id));
        return (List<Note>) criteria.list();
    }

    @Override
    public Note findById(long noteId) {
        return getByKey(noteId);
    }

    @Override
    public Note updateNote(Note note) {
        return update(note);
    }

    @Override
    public void delete(Long noteId) {
        delete(getByKey(noteId));
    }
}
