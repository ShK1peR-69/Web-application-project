package ru.kazan.kpfu.itis.master.astafyev.app.security;

import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Password encoder for Spring Security
 *****/

public class PasswordEncoder implements org.springframework.security.authentication.encoding.PasswordEncoder {

    @Override
    public String encodePassword(String password, Object o) {
        return Methods.hashPass(password);
    }

    @Override
    public boolean isPasswordValid(String dbPass, String userPass, Object o) {
        userPass = Methods.hashPass(userPass);
        return dbPass.equals(userPass);
    }
}
