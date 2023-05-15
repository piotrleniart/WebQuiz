package engine.service;

import engine.model.User;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }

    public User getUserByEmail(String name) {
        return userRepo.findByEmail(name);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }


    // tbd
    @Override
    public User updateUserById(Long id, User userToUpdate) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
