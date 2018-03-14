package com.tsalapova.springaop.service;

import com.tsalapova.springaop.entity.User;

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
