package com.tsalapova.notebook.service.impl;

import com.tsalapova.notebook.dao.UserDao;
import com.tsalapova.notebook.entity.User;
import com.tsalapova.notebook.generator.HashGenerator;
import com.tsalapova.notebook.service.UserService;
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
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean login(User currentUser) {
        User user = userDao.findByLogin(currentUser.getLogin());
        if (user != null && user.getId() != -1L) {
            String hash = new HashGenerator().generateHash(currentUser.getPassword(), user.getSalt());
            if (hash.equals(user.getPassword())) {
                currentUser.setId(user.getId());
                return true;
            }
        }
        return false;
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
