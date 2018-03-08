package project.com.managment.services;



        import org.springframework.security.core.userdetails.User.UserBuilder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import project.com.managment.domain.User;
        import project.com.managment.repositories.UserRepository;




public class UserDetailsServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Long userNameLong = Long.parseLong(username, 10);
        User user =getUserByIdNumber(userNameLong);
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
    public User getUserByIdNumber(Long username) {
       User foundUser = userRepository.findByIdNumber(username);
       // User foundUser = userRepository.getDistinctFirstByIdNumber(98L);
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
