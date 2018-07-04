package project.com.managment.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.com.managment.domain.Payments;
import project.com.managment.domain.Project;
import project.com.managment.domain.Purchase;
import project.com.managment.domain.User;

import java.util.Set;

public interface PaymentsService  {


    Set<Payments> getAllPayments();
    Payments createNewPayments(Long projectid,Payments payments);
    Payments paymentsById(Long id);
    Payments updatePayments(Long id,Long projectid,Payments payments);
}
