package project.com.managment.repositories;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.com.managment.domain.Role;
import project.com.managment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdNumber(Long idNumber);
   List<User> findAllByLastName(String lastname);
    List<User> findAllByRole(Role role);
  // List<User> findByLastname(String lastname);
    User findById(Long id);
    User getDistinctFirstByIdNumber(Long id );
}
