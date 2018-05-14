package project.com.managment.services;

import project.com.managment.domain.Time;
import project.com.managment.domain.WorkDay;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import project.com.managment.domain.Project;
        import project.com.managment.domain.*;

        import java.time.LocalDate;
        import java.util.Date;
        import java.util.Set;


        import project.com.managment.domain.Project;
        import project.com.managment.domain.Purchase;

        import java.util.List;
        import java.util.Set;

public interface WorkerPaymentService {
    public List <WorkerPayment> getAllWorkerPayments();
    public  List <WorkerPayment> getAllWorkerPaymentsEmployee(Long employeeId);
    public  List <WorkerPayment> getAllWorkerPaymentsManager(Long managerId);
    public WorkerPayment createNewWorkerPayment( Long workerid,Long userid,WorkerPayment workerPayment);
    public WorkerPayment  getWorkerPaymentId(Long workerPaymentid);
    public  WorkerPayment DeleteWorkerPayment(Long workerpaymentid);
    public  WorkerPayment updateWorkerPayment(Long workerpaymentid, WorkerPayment workerPayment);

}
