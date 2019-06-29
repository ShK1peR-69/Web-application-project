package ru.kazan.kpfu.itis.master.astafyev.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kazan.kpfu.itis.master.astafyev.app.entities.User;
import ru.kazan.kpfu.itis.master.astafyev.app.repositories.UserRepository;

import java.util.List;

/*****
 * @author Igor Astafyev
 * May, 2019
 *****/

@Service
public class UserService {

    @Autowired
    private UserRepository usersRepository;

    @Transactional
    public List<User> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    @Transactional
    public void addNewUser(User user) {
        usersRepository.addNewUser(user);
    }

    @Transactional
    public User getUserById(long id) {
        return usersRepository.getUserById(id);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

    @Transactional
    public void deleteUser(User user) {
        usersRepository.deleteUserFromDB(user);
    }
}
