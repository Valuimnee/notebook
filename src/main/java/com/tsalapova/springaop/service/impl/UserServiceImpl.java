package com.tsalapova.springaop.service.impl;

import com.tsalapova.springaop.dao.UserDao;
import com.tsalapova.springaop.entity.User;
import com.tsalapova.springaop.generator.HashGenerator;
import com.tsalapova.springaop.service.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(User currentUser) {
        User user;
        user = userDao.findByLogin(currentUser.getLogin());
        if (user != null) {
            String hash = new HashGenerator().generateHash(currentUser.getPassword(), user.getSalt());
            if (hash.equals(user.getPassword())) {
                currentUser.setId(user.getId());
            }
        }
        return true;
    }

    @Override
    public boolean update(User currentUser, User newUser) {
        HashGenerator hashGenerator = new HashGenerator();
        User user = userDao.findByLogin(currentUser.getLogin());
        if (!user.getLogin().equals(newUser.getLogin())) {
            if (userDao.findIdByLogin(newUser.getLogin()) != -1L) {
                return false;
            }
            userDao.updateLogin(user.getId(), newUser.getLogin());
            user.setLogin(newUser.getLogin());
        }
        if (currentUser.getPassword().isEmpty() && newUser.getPassword().isEmpty()) {
            return true;
        }
        if (!user.getPassword().equals(hashGenerator.generateHash(currentUser.getPassword(), user.getSalt()))) {
            return false;
        }
        Pair<String, String> hashSalt = hashGenerator.generateHashSalt(newUser.getPassword());
        user.setPassword(hashSalt.getKey());
        user.setSalt(hashSalt.getValue());
        userDao.updateHashSalt(user);
        return true;
    }

    @Override
    public boolean register(User user) {
        if (userDao.findIdByLogin(user.getLogin()) != -1L) {
            return false;
        }
        Pair<String, String> hashSalt = new HashGenerator().generateHashSalt(user.getPassword());
        user.setPassword(hashSalt.getKey());
        user.setSalt(hashSalt.getValue());
        User newUser = userDao.add(user);
        System.err.println(newUser);
        newUser = userDao.findByLogin(user.getLogin());
        user.setId(newUser.getId());
        return true;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findById(long userId) {
        return userDao.findById(userId);
    }
}
