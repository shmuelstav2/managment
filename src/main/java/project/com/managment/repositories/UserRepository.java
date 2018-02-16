package project.com.managment.repositories;
import project.com.managment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
   // User findByIdNumber(int idNumber);

    //User findById(Long id);
}
