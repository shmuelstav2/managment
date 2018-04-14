package project.com.managment.services;

import org.springframework.stereotype.Service;
import project.com.managment.domain.Project;
import project.com.managment.domain.Time;
import project.com.managment.domain.User;
import project.com.managment.domain.WorkDay;
import project.com.managment.repositories.workDayRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


        import org.springframework.stereotype.Service;
        import org.springframework.stereotype.Service;
        import project.com.managment.domain.*;
        import project.com.managment.repositories.*;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.time.*;
        import java.util.Date;
        import java.util.HashSet;

        import java.util.List;
        import java.util.Set;

@Service
public class WorkerPaymentServiceImpl implements WorkerPaymentService {


    private WorkerPaymentRepository workerPaymentRepository;
    private UserService userService;


    public WorkerPaymentServiceImpl(  WorkerPaymentRepository workerPaymentRepository,UserService userService){
        this.workerPaymentRepository =  workerPaymentRepository;
        this.userService = userService;

    }

    public List <WorkerPayment> getAllWorkerPayments(){
        List workDayList =  workerPaymentRepository.findAll();
        return workDayList;
    }

    public WorkerPayment createNewWorkerPayment( Long workerid,Long userid,WorkerPayment workerPayment){
        User currentUser = userService.getUserById(workerid);
        User currentManager = userService.getUserById(userid);
        workerPayment.setWorker(currentUser);
        workerPayment.setManager(currentManager);
        return workerPaymentRepository.save(workerPayment);
    }

}
