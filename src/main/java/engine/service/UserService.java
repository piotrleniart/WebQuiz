package engine.service;


import engine.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String name);

    User saveUser(User user);

    User updateUserById(Long id, User userToUpdate);

    void deleteUserById(Long id);
}
