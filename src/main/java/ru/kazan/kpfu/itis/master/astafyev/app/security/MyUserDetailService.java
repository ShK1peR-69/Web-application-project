package ru.kazan.kpfu.itis.master.astafyev.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.controllers.services.UserService;

/*****
 * @author Igor Astafyev
 * May, 2019
 *****/

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with mail " + email + " not found!");
        }
        return new MyUserDetail(user);
    }
}