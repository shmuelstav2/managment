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

   /* Set<Project> getAllProjects();
    public Purchase createNewPurchase(Long id, Purchase purchase);
//    User getUserByIdNumber(int idNumber);

    Project createNewProject(Project project);
    Project getProjectById(Long id);
    Set<Project> getProjectByPartner(int patner);
    Set<Project> getProjectByActive(boolean active);
    Project updateProject(Long idNumber,Project project);
    Project updateProjectImage(Long idNumber,String imageLink);
    */
}
