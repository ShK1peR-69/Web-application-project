package ru.kazan.kpfu.itis.master.astafyev.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*****
 * @Created by Igor Astafyev
 * May, 2019
 *****/

public class MyUserDetail implements UserDetails {

    /**
     * запись о пользователе из БД
     */
    private User user;

    MyUserDetail(User user) {
        this.user = user;
    }

    /**
     * @return коллекция прав доступа пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return grantedAuthorities;
    }

    /**
     * hash пароля пользователя
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Логин (email) пользователя
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * @return флаг, что срок действия аккаунта еще не истек, он активен
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return флаг, что пользователь не заблокирован администраторами сайта
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return флаг, что срок действия пароля еще не истек, он активен
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return флаг, что пользователь включен и подтвержден
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
