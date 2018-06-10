package project.com.managment.services;

import project.com.managment.domain.Project;

import java.util.Set;


        import project.com.managment.domain.Project;
import project.com.managment.domain.Purchase;

import java.util.List;
        import java.util.Set;

public interface PurchaseService {

    public Set<Purchase> getAllPurchases();
    public Purchase updatePurchaseImage(Long id, String imageLink) ;
    public Purchase createNewPurchase(Long id,Purchase purchase);
     public Purchase updatePurchase(Long purchaseid,Purchase purchase);
     public Purchase getPurchase(Long purchaseid);
    /*
    Set<Project> getAllProjects();

//    User getUserByIdNumber(int idNumber);

    Project createNewProject(Project project);
    Project getProjectById(Long id);
    Set<Project> getProjectByPartner(int patner);
    Set<Project> getProjectByActive(boolean active);
    Project updateProject(Long idNumber,Project project);
    Project updateProjectImage(Long idNumber,String imageLink);
    */
}
