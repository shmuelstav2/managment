package project.com.managment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.managment.domain.Project;
import project.com.managment.domain.User;

import java.util.List;

        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import project.com.managment.domain.User;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {
}
