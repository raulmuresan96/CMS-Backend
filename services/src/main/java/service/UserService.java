package service;

import com.ubb.cms.User;
import com.ubb.cms.utils.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserRepository;
import service.validator.ValidatorInterface;

import java.util.List;

/**
 * Created by Raul on 24/04/2017.
 */

public class UserService extends BaseService<User> {

    private UserRepository userRepository;

    @Autowired
    public UserService(ValidatorInterface validator, UserRepository userRepository) {
        super(validator);
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void addUser(int id, String username, String password, String email, String name, String surname, UserTag tag) {
        addUser(new User(id, username, password, email, name, surname, tag));
    }

    public void addUser(User user) {
        validate(user);
        userRepository.add(user);
    }


    public void deleteUser(int key) {
        userRepository.delete(key);
    }

    public User findById(int key) {
        return userRepository.findById(key);
    }


    public void updateUser(int key, User newUser) {
        userRepository.update(key, newUser);
    }

    public User checkUser(User user) {
        return userRepository.checkUser(user.getUsername(), user.getPassword());
    }


}
