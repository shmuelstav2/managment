package project.com.managment.services;
import project.com.managment.domain.User;
import java.util.List;

        import java.util.List;

/**
 * Created by jt on 9/26/17.
 */
public interface UserService {

    List<User> getAllUsers();

    User getUserByIdNumber(int idNumber);
    User createNewUser(User user);
    public User getUserById(Long id);
}
