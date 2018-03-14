package com.tsalapova.springaop.controller;

import com.tsalapova.springaop.entity.User;
import com.tsalapova.springaop.service.UserService;
import com.tsalapova.springaop.util.DocumentConstant;
import com.tsalapova.springaop.util.PageConstant;
import com.tsalapova.springaop.util.RequestConstant;
import com.tsalapova.springaop.util.SessionConstant;
import com.tsalapova.springaop.validator.ParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap model, HttpSession session,
                        @RequestParam("login") String login, @RequestParam("password") String password){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        if (!new ParameterValidator().validateUserInfo(user)) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG);
            return PageConstant.LOGIN;
        }
        if(!userService.login(user)){
            return PageConstant.MAIN;
        }
        if (user.getId() != -1L) {
            setUserSession(session, user);
            return PageConstant.MAIN;
        } else {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG);
            return PageConstant.LOGIN;
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        clearUserSession(session);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(ModelMap model, HttpSession session, @RequestParam("login") String login,
                           @RequestParam("password") String password, @RequestParam("password2") String password2){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        ParameterValidator validator = new ParameterValidator();
        if (!(validator.validateLogin(login) && validator.validateConfirmPassword(password, password2))) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_INFO);
            return PageConstant.REGISTER;
        }
        if (!userService.register(user)) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_LOGIN);
            return PageConstant.REGISTER;
        }
        setUserSession(session, user);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/account")
    public String viewAccount(ModelMap model, @SessionAttribute("login") String login){
        model.addAttribute(RequestConstant.LOGIN, login);
        model.addAttribute(RequestConstant.CONTENT, RequestConstant.USER_ACCOUNT);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/edit-account", method = RequestMethod.POST)
    public String editAccount(ModelMap model, HttpSession session, @SessionAttribute("id") Long id, @SessionAttribute("login") String login,
                              @RequestParam("login") String newLogin, @RequestParam("password") String password,
                              @RequestParam("new-password") String newPassword, @RequestParam("new-password2") String newPassword2){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        User newUser=new User();
        newUser.setLogin(newLogin);
        newUser.setPassword(newPassword);
        ParameterValidator validator = new ParameterValidator();
        if (!validator.validateLogin(newLogin) || !(password.isEmpty() && newPassword.isEmpty() && newPassword2.isEmpty() ||
                validator.validatePassword(password) && validator.validateConfirmPassword(newPassword, newPassword2))) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_INFO);
            model.addAttribute(RequestConstant.CONTENT, RequestConstant.USER_ACCOUNT);
            return PageConstant.MAIN;
        }
        if(!userService.update(user, newUser)){
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_LOGIN);
            model.addAttribute(RequestConstant.CONTENT, RequestConstant.USER_ACCOUNT);
            return PageConstant.MAIN;
        }
        session.setAttribute(SessionConstant.LOGIN, newLogin);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/delete-account")
    public String deleteAccount(HttpSession session, @SessionAttribute("id") Long id){
        userService.deleteById(id);
        clearUserSession(session);
        return PageConstant.MAIN;
    }

    private void setUserSession(HttpSession session, User user) {
        session.setAttribute(SessionConstant.ID, user.getId());
        session.setAttribute(SessionConstant.LOGIN, user.getLogin());
        session.setAttribute(SessionConstant.ROLE, "user");
    }

    private void clearUserSession(HttpSession session) {
        Enumeration<String> attributes = session.getAttributeNames();
        String attribute;
        while (attributes.hasMoreElements()) {
            attribute = attributes.nextElement();
            if (!attribute.equals(DocumentConstant.LANG)) {
                session.removeAttribute(attribute);
            }
        }
    }
}
