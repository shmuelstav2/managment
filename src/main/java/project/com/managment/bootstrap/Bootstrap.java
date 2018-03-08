package project.com.managment.bootstrap;
import project.com.managment.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Component;
import project.com.managment.repositories.UserRepository;
import project.com.managment.repositories.ProjectRepository;
import project.com.managment.services.ProjectServiceImpl;

import java.lang.reflect.Array;

import static project.com.managment.domain.Role.MAINMANAGER;
import static project.com.managment.domain.Role.USER;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
   // private final ProjectServiceImpl projectService;
    //private final CustomerRepository customerRepository;
    /*private final VendorRepository vendorRepository;
  */
    public Bootstrap(UserRepository userRepository,ProjectRepository projectRepository
            //,ProjectServiceImpl projectService
    /*, CustomerRepository customerRepository, VendorRepository vendorRepository*/
    )
    {

        this.userRepository = userRepository;
        this.projectRepository =projectRepository;
      //  this.projectService = projectService;
     /*   this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        */
    }

    @Override
    public void run(String... args) throws Exception {

         loadUsers();
         loadProjects();
  //      loadCustomers();
   //     loadVendors();
    }
    private void loadUsers() {
        User user1 = new User();
        user1.setFirstName("Vendor 1");
        user1.setRole(USER);
        user1.setIdNumber("036913192");
        user1.setPassword("1234");
        user1.setLastName("swefew");
        user1.setIdNumber("1321313");
        //user1.setRole();
        userRepository.save(user1);

        //Vendor vendor2 = new Vendor();
        //vendor2.setName("Vendor 2");
        //vendorRepository.save(vendor2);

    }

    private void loadProjects(){
        Project project1 = new Project();
        Address address1 = new Address();
        address1.setFullAdress("lod");
        Concat cn1 = new Concat();
        cn1.setFirstName("ihuih");
        Concat cn2 = new Concat();
        cn2.setFirstName("ihuikjlh");
        project1.addConcat(cn1);
        project1.addConcat(cn2);
        Purchase p1 = new Purchase();
        p1.setAmount(6);
        p1.setComment("rstttrg rtyg6 g  54t54 4g45g54g54");
        p1.setOperatorId("036913192");
        project1.addPurcase(p1);
       // project1.setOwner(2);
        Location lc1 =new Location();
        lc1.setLatitude(453534);
        lc1.setLongitude(5476547);
        address1.setLocation(lc1);
        project1.setAddress(address1);
        projectRepository.save(project1);
     //   System.out.print(projectService.getAllProjects());

    }
}