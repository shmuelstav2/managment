package project.com.managment.services;

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
public class ProjectServiceImpl implements ProjectService {


    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository= projectRepository;
    }

    //@Override
    //public List<Project> getAllProjects() {
     //   return projectRepository.findAll().stream().collect(Collectors.toList());
    //}

    public Set<Project> getAllProjects() {
        //log.debug("I'm in the service");

        Set<Project> projectSet = new HashSet<>();
        projectRepository.findAll().iterator().forEachRemaining(projectSet::add);
        return projectSet;
    }


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



    private Project saveAndReturnProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return savedProject;
    }
}
