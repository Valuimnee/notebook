package com.tsalapova.notebook.service;

import com.tsalapova.notebook.entity.User;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
public interface UserService {
    User findById(long userId);

    boolean login(User currentUser);

    boolean update(User currentUser, User newUser);

    boolean register(User user);

    void deleteById(Long id);
}
