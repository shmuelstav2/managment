package project.com.managment.bootstrap;
import project.com.managment.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Component;
import project.com.managment.repositories.PaymentsRepository;
import project.com.managment.repositories.UserRepository;
import project.com.managment.repositories.ProjectRepository;
import project.com.managment.services.ProjectServiceImpl;

import java.lang.reflect.Array;
import java.time.LocalTime;

import static project.com.managment.domain.Role.MAINMANAGER;


/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PaymentsRepository paymentsRepository;
   // private final ProjectServiceImpl projectService;
    //private final CustomerRepository customerRepository;
    /*private final VendorRepository vendorRepository;
  */
    public Bootstrap(UserRepository userRepository,ProjectRepository projectRepository, PaymentsRepository paymentsRepository
            //,ProjectServiceImpl projectService
    /*, CustomerRepository customerRepository, VendorRepository vendorRepository*/
    )
    {

        this.userRepository = userRepository;
        this.projectRepository =projectRepository;
        this.paymentsRepository = paymentsRepository;
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
        user1.setRole(MAINMANAGER);
        user1.setIdNumber(5675756756L);
        user1.setPassword("1234");
        user1.setLastName("swefew");
        user1.setIdNumber(1321313L);
        user1.setPayDay(230);
        user1.setAdditionHourPay(13);
        userRepository.save(user1);

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
        project1.setActive(true);
        Purchase p1 = new Purchase();
        p1.setAmount(6);

        p1.setComment("rstttrg rtyg6 g  54t54 4g45g54g54");
        p1.setOperatorId("036913192");
       // p1.
       // p1.get
        project1.addPurcase(p1);
       // project1.setOwner(2);
        Location lc1 =new Location();
        lc1.setLatitude(453534);
        lc1.setLongitude(5476547);
        address1.setLocation(lc1);
        project1.setAddress(address1);



        Project project2 = new Project();
        Address address2 = new Address();
        address1.setFullAdress("lod");
        Concat cn3 = new Concat();
        cn2.setFirstName("ihuih");
        Concat cn4 = new Concat();
        cn3.setFirstName("ihuikjlh");
        project2.addConcat(cn3);
        project2.addConcat(cn4);
        project2.setActive(true);
        Purchase p2 = new Purchase();
        project2.setAddress(address1);
        p2.setAmount(6);
        /*
             Add workDays to project
         */
        WorkDay wd1 = new WorkDay();
        wd1.setCheckIn( LocalTime.now());
        project1.addWorkDay(wd1);
        projectRepository.save(project1);
        projectRepository.save(project2);

        Payments pay1 = new Payments();
        pay1.setPayer("retre");
        pay1.setComment("number 2");
        pay1.setProject(project1);
        Payments pay2 = new Payments();
        pay2.setPayer("Dan");
        pay2.setComment("number 2");
        pay2.setProject(project1);
        paymentsRepository.save(pay1);
        paymentsRepository.save(pay2);

     //   System.out.print(projectService.getAllProjects());

    }
}