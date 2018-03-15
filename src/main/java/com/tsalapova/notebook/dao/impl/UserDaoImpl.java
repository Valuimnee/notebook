package com.tsalapova.notebook.dao.impl;

import com.tsalapova.notebook.dao.AbstractDao;
import com.tsalapova.notebook.dao.UserDao;
import com.tsalapova.notebook.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Override
    public User findByLogin(String login) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        return (User) criteria.uniqueResult();
    }

    @Override
    public long findIdByLogin(String login) {
        User user = findByLogin(login);
        return user == null ? -1L : user.getId();
    }

    @Override
    public void updateLogin(long id, String login) {
        Query query = getSession().createSQLQuery("UPDATE `user` SET `login`=:login WHERE `id`=:id");
        query.setString("login", login);
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateHashSalt(User user) {
        update(user);
    }

    @Override
    public void deleteById(long id) {
        Query query = getSession().createSQLQuery("DELETE FROM `user` WHERE id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public User findById(long id) {
        return getByKey(id);
    }
}
