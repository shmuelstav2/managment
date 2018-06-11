package project.com.managment.services;

import project.com.managment.domain.Project;
import project.com.managment.domain.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


import project.com.managment.domain.Project;
import project.com.managment.domain.Purchase;

import java.util.List;
import java.util.Set;

public interface WorkDayService {
    public WorkDay DeleteWorkDayId (Long id);
    public  WorkDay  updateWorkDayDate (Long id, Date date) ;
    public  WorkDay getWorkDayId (Long workdayid);
    public List <WorkDay> getWorkDay(Long userid);
    public List<WorkDay> getAllWorkDays();
    public  WorkDay   updateWorkDayCheckIn (Long id, Time time) ;
    public  WorkDay   updateWorkDayCheckOut (Long id, Time time) ;
    public WorkDay createNewWorkDay(Long projectId,Long userId,WorkDay workDay);
    public WorkDay  addCheckIn(Long projectid,Long userid);
    public WorkDay addCheckOut(Long workdayid);
}
