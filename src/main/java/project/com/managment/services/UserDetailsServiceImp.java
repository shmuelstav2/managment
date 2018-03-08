package project.com.managment.services;



        import org.springframework.security.core.userdetails.User.UserBuilder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import project.com.managment.domain.User;
        import project.com.managment.repositories.UserRepository;

        import static project.com.managment.domain.Role.USER;


public class UserDetailsServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    /*Here we are using dummy data, you need to load user data from
     database or other third party application*/
        User user =getUserByIdNumber(username);
        //user.g
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getRole().toString());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
    public User getUserByIdNumber(String username) {
       // User foundUser = userRepository.findByIdNumber(username);
        User foundUser = userRepository.getDistinctFirstByIdNumber(036913192);
        return foundUser;
    }

    //private User findUserbyUername(String username) {
        /*if (
                username.equalsIgnoreCase("admin")) {
    */
     //       User us1 = userRepository.findByIdNumber(username);
      //      return us1;
            /*
        } else {
            return null;
        }*/

    //}

}
