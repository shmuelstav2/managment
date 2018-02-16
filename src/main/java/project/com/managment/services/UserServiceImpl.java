package project.com.managment.services;

import org.springframework.stereotype.Service;
import project.com.managment.domain.User;
import project.com.managment.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
      return userRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public User getUserByIdNumber(int idNumber) {
        return null;
    }

    @Override
    public User createNewUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User getUserById(Long id) {
        User foundUser = userRepository.findOne(id);
        return foundUser;
    }
}
