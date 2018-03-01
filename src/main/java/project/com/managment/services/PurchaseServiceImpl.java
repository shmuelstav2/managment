package project.com.managment.services;

import org.springframework.stereotype.Service;



        import org.springframework.stereotype.Service;
import project.com.managment.domain.*;
import project.com.managment.repositories.*;

import java.util.HashSet;

        import java.util.Set;
        import java.util.stream.Collectors;


        import org.springframework.stereotype.Service;
        import project.com.managment.domain.Project;
        import project.com.managment.domain.Role;
        import project.com.managment.domain.User;
        import project.com.managment.repositories.ProjectRepository;
        import project.com.managment.repositories.UserRepository;

        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;
        import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {


    private PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl( PurchaseRepository purchaseRepository) {
        this.purchaseRepository= purchaseRepository;
    }

   // @Override
    //public List<Project> getAllProjects() {
      // return projectRepository.findAll().stream().collect(Collectors.toList());
    //}

    public Set<Purchase> getAllPurchases() {
        //log.debug("I'm in the service");

        Set<Purchase> purchaseSet = new HashSet<>();
        purchaseRepository.findAll().iterator().forEachRemaining(purchaseSet::add);
        return purchaseSet;
    }

    //@Override
    public Purchase createNewPurchase(Purchase purchase) {

        Purchase savedPurchase = purchaseRepository.save(purchase);
        return savedPurchase;
    }

    @Override
    public Purchase updatePurchaseImage(Long id, String imageLink) {
        Purchase foundPurchase = purchaseRepository.findOne(id);
        foundPurchase.setImageLink(imageLink);
        return saveAndReturnPurchase( foundPurchase);
    }


    private Purchase saveAndReturnPurchase(Purchase purchase) {
        Purchase savedPurchase = purchaseRepository.save(purchase);
        return savedPurchase;
    }
/*
    @Override
    public Project createNewProject(Project project) {

        Project savedProject = projectRepository.save(project);
        return savedProject;
    }

    @Override
    public Project getProjectById(Long id) {
        Project foundProject = projectRepository.findOne(id);
        return foundProject;
    }

    @Override
    public Set<Project> getProjectByPartner(int patner) {
        Set<Project> projectSet = new HashSet<>();
        projectRepository.findAllByPartner(patner).iterator().forEachRemaining(projectSet::add);
        return projectSet;
    }

    @Override
    public Set<Project> getProjectByActive(boolean active) {
        Set<Project> projectSet = new HashSet<>();
        projectRepository.findAllByActive(active).iterator().forEachRemaining(projectSet::add);
        return projectSet;
    }

    @Override
    public Project updateProject(Long idNumber,Project project) {
        Project foundProject = projectRepository.findOne(idNumber);
        project.setId(foundProject.getId());
        return saveAndReturnProject(project);
    }

    @Override
    public Project updateProjectImage(Long idNumber, String imageLink) {
        Project foundProject = projectRepository.findOne(idNumber);
        foundProject.setImageLink(imageLink);
        return saveAndReturnProject( foundProject);
    }


    private Project saveAndReturnProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return savedProject;
    }*/
}
