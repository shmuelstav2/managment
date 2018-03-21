package project.com.managment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.managment.domain.User;
import project.com.managment.domain.WorkDay;

import java.time.LocalDate;
import java.util.Date;

public interface workDayRepository  extends JpaRepository<WorkDay, Long> {
    WorkDay getDistinctByDateIsLikeAndUser( LocalDate date, User user);
    WorkDay getDistinctByUserEquals( User user);
    WorkDay getDistinctFirstByDateAndUser( LocalDate date,User user);

}

