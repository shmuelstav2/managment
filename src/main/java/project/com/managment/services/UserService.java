package project.com.managment.services;
import project.com.managment.domain.Role;
import project.com.managment.domain.User;
import java.util.List;

        import java.util.List;

/**
 * Created by jt on 9/26/17.
 */
public interface UserService {

    List<User> getAllUsers();
    User getUserByIdNumber(Long idNumber);
    User createNewUser(User user);
    public User getUserById(Long id);
    List<User> getUsersByRole(Role roleDescription);
    public User updateUser(Long  idNumber, User user);


}
