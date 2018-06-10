package project.com.managment.services;

import org.springframework.stereotype.Service;
import project.com.managment.domain.*;
import project.com.managment.repositories.PaymentsRepository;
import project.com.managment.repositories.ProjectRepository;
import project.com.managment.repositories.PurchaseRepository;
import project.com.managment.repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentsServiceImpl implements PaymentsService{

    private PaymentsRepository paymentsRepository;
    private ProjectService projectService;

    public PaymentsServiceImpl(  PaymentsRepository paymentsRepository,ProjectService projectService) {
        this.paymentsRepository= paymentsRepository;
        this.projectService = projectService;
    }


    @Override
    public Set<Payments> getAllPayments() {
        Set<Payments> payments = new HashSet<>();
        paymentsRepository.findAll().iterator().forEachRemaining(payments::add);
        return payments;
    }


    @Override
   public Payments createNewPayments(Long projectid,Payments payments){
        Project current = projectService.getProjectById(projectid);
        if(current!=null && current.getActive()==true){
            payments.setProject(current);
            payments.setDate(LocalDate.now());
            Payments savedPayments = paymentsRepository.save(payments);
            return savedPayments;
        }
        else{
            return null;
        }

    }

    @Override
    public Payments paymentsById(Long id){
        return(paymentsRepository.findOne(id));
    }

    public Payments updatePayments(Long id,Long projectid,Payments payments){
       Project project = paymentsRepository.findOne(id).getProject();
        if(paymentsRepository.findOne(id)==null || project == null){
           return null;
        }
            payments.setProject(project);
            Payments foundPayment = paymentsRepository.findOne(id);
            payments.setId(foundPayment.getId());
            payments.setDate(LocalDate.now());
            return (paymentsRepository.save(payments));
        }

}
