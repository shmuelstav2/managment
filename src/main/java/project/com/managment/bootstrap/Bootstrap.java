package project.com.managment.bootstrap;
import project.com.managment.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Component;
import project.com.managment.repositories.UserRepository;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;

    //private final CustomerRepository customerRepository;
    /*private final VendorRepository vendorRepository;
  */
    public Bootstrap(UserRepository userRepository
    /*, CustomerRepository customerRepository, VendorRepository vendorRepository*/
    )
    {

        this.userRepository = userRepository;
     /*   this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        */
    }

    @Override
    public void run(String... args) throws Exception {

         loadUsers();
  //      loadCustomers();
   //     loadVendors();
    }
    private void loadUsers() {
        User user1 = new User();
        user1.setFirstName("Vendor 1");
        user1.setLastName("swefew");
        //user1.setRole();
        userRepository.save(user1);

        //Vendor vendor2 = new Vendor();
        //vendor2.setName("Vendor 2");
        //vendorRepository.save(vendor2);

    }


    /*
    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);

        System.out.println("Categories Loaded: " + categoryRespository.count());
    }

    private void loadCustomers() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1l);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");

        customerRepository.save(customer2);

        System.out.println("Customers Loaded: " + customerRepository.count());
    }*/
}