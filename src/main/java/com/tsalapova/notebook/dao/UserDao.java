package com.tsalapova.notebook.dao;

import com.tsalapova.notebook.entity.User;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
public interface UserDao {
    /**
     * Method finds user by his login
     *
     * @param login login of the user
     * @return {@code User} found user or {@code null}
     */
    User findByLogin(String login);

    /**
     * Method find id of user by his login
     *
     * @param login login of certain user
     * @return {@code long} id of found user or {@code null}
     */
    long findIdByLogin(String login);

    /**
     * Method updates login of user by his id
     *
     * @param id    id of certain user
     * @param login new login
     */
    void updateLogin(long id, String login);

    /**
     * Method sets new password (ans salt) to the specified user
     *
     * @param user changed user
     */
    void updateHashSalt(User user);

    /**
     * Method deletes user by his id (and also his client info and account for client)
     *
     * @param id id of user to delete
     */
    void deleteById(long id);

    User findById(long id);

    User add(User user);
}
