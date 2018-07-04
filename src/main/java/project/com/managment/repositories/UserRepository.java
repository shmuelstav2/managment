package project.com.managment.repositories;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project.com.managment.domain.Role;
import project.com.managment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
     User findByIdNumber(BigInteger idNumber);
     List<User> findAllByLastName(String lastname);
     List<User> findAllByRole(Role role);
     User findById(Long id);
     User getDistinctFirstByIdNumber(BigInteger id );
}
