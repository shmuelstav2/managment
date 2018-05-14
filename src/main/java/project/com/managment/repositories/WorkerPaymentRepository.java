package project.com.managment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.managment.domain.WorkDay;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.managment.domain.User;
import project.com.managment.domain.WorkDay;
import project.com.managment.domain.WorkerPayment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WorkerPaymentRepository  extends JpaRepository<WorkerPayment, Long> {
  // List <WorkerPayment> findAllByWorker(Long id);
   List <WorkerPayment> findAllByWorkerId (Long id);
   List <WorkerPayment> findAllByManagerId (Long id);
}
