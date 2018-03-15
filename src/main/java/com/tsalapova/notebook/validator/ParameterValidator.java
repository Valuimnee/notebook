package com.tsalapova.notebook.validator;


import com.tsalapova.notebook.entity.Note;
import com.tsalapova.notebook.entity.User;

/**
 * @author TsalapovaMD
 * @version 1.0, 1/3/2018
 */
public class ParameterValidator {
    private final static String PASSWORD_REGEX = "^[^ ]{8,40}$";
    private final static String LOGIN_REGEX = "^[-\\w.]{4,20}$";

    public boolean validateLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    public boolean validatePassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    public boolean validateConfirmPassword(String password, String password2) {
        return password != null && password.equals(password2) && password.matches(PASSWORD_REGEX);
    }

    public boolean validateUserInfo(User user) {
        return validateLogin(user.getLogin()) && validatePassword(user.getPassword());
    }

    public boolean validateNote(Note note) {
        return note.getContent() != null && note.getContent().length() <= 1000 &&
                (note.getTitle() == null || note.getTitle().length() <= 80);
    }
}
