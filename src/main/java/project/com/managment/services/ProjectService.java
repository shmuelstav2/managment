package project.com.managment.services;

import project.com.managment.domain.Project;
import project.com.managment.domain.Purchase;

import java.util.List;
import java.util.Set;

public interface ProjectService {

    Set<Project> getAllProjects();
    public Purchase createNewPurchase(Long id, Purchase purchase);
    Project createNewProject(Project project);
    Project getProjectById(Long id);
    Set<Project> getProjectByPartner(int patner);
    Set<Project> getProjectByActive(boolean active);
    Project updateProject(Long idNumber,Project project);
    Project updateProjectImage(Long idNumber,String imageLink);
}
