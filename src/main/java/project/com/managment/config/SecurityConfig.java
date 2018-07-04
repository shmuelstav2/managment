
package project.com.managment.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.com.managment.repositories.UserRepository;
import project.com.managment.services.UserDetailsServiceImp;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserRepository userRepository;
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/**", "/index").hasAnyRole("MAINMANAGER","SECONDMANAGER", "EMPLOYEE")
               // .antMatchers("/api/users/**").hasRole("MAINMANAGER")
                .and()
                .httpBasic();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp(userRepository);
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
