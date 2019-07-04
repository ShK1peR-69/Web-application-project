package ru.kazan.kpfu.itis.master.astafyev.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Password encoder for Spring Security
 *****/

public class PasswordEncoder extends Md5PasswordEncoder {

    @Autowired
    private Methods methods;

    @Override
    public String encodePassword(String password, Object o) {
        return methods.hashPass(password);
    }

    @Override
    public boolean isPasswordValid(String dbPass, String userPass, Object o) {
        userPass = methods.hashPass(userPass);
        return dbPass.equals(userPass);
    }
}
