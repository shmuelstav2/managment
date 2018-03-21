
package project.com.managment.services;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import project.com.managment.domain.*;
import project.com.managment.repositories.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;

import java.util.Set;

@Service
public class WorkDayServiceImpl implements WorkDayService {


    private workDayRepository workDayRepository;
    private ProjectService projectService;
    private UserService userService;

    public WorkDayServiceImpl(  workDayRepository workDayRepository,ProjectService projectService ,UserService userService) {
        this.workDayRepository=  workDayRepository;
        this.projectService = projectService;
        this.userService = userService;
    }



    @Override
    public Set<WorkDay> getAllWorkDays() {
        Set<WorkDay> workDaysSet = new HashSet<>();
        workDayRepository.findAll().iterator().forEachRemaining(workDaysSet::add);
        return workDaysSet;
    }



    @Override
   public WorkDay createNewWorkDay(Long projectId,Long userId,WorkDay workDay) {
        Project current = projectService.getProjectById(projectId);
        workDay.setProject(current);
        workDay.setUser(userService.getUserById(userId));
        WorkDay savedWorkDay = workDayRepository.save(workDay);
        return savedWorkDay;
    }

    public WorkDay  addCheckIn(Long projectid,Long userid) {
        Project current = projectService.getProjectById(projectid);
        WorkDay newWorkDay = new WorkDay();
        newWorkDay.setCheckIn(LocalTime.now().plusMinutes(10));
        newWorkDay.setDate(LocalDate.now());
        newWorkDay.setProject(current);
        newWorkDay.setUser(userService.getUserById(userid));
        WorkDay savedWorkDay = workDayRepository.save(newWorkDay);
        return savedWorkDay;
    }

    @Override
    public WorkDay addCheckOut(Long workdayid) {
        WorkDay newWorkDay = workDayRepository.findOne(workdayid);
        newWorkDay.setCheckOut(LocalTime.now().plusMinutes(-5));
        newWorkDay.calcDailySalary(newWorkDay.getCheckOut());
        WorkDay savedWorkDay = workDayRepository.save(newWorkDay);
        return savedWorkDay;
    }

    @Override
    public WorkDay getWorkDay(Long userid,  LocalDate date) {
        System.out.print(date);
        User user =userService.getUserById(userid);
        WorkDay newWorkDay = workDayRepository.getDistinctByDateIsLikeAndUser(date,user);
        return newWorkDay;
    }

    @Override
    public WorkDay updateWorkDayDate(Long id, Date date) {
        System.out.print(date);
        return null;
    }

    @Override
    public  WorkDay getWorkDayId (Long workdayid){
        WorkDay foundWorkDay = workDayRepository.findOne(workdayid);
        return foundWorkDay;
    }


    @Override
    public  WorkDay updateWorkDayCheckIn (Long id, Time time) {
        WorkDay foundWorkDay = workDayRepository.findOne(id);
        LocalTime localTime2 = LocalTime.of(time.getHours(), time.getMinutes());
        foundWorkDay.setCheckIn(localTime2);
        foundWorkDay.calcDailySalary(foundWorkDay.getCheckOut());
        return saveAndReturnWorkDay(foundWorkDay);
    }
    @Override
    public  WorkDay updateWorkDayCheckOut (Long id, Time time) {
        WorkDay foundWorkDay = workDayRepository.findOne(id);
        LocalTime localTime2 =  LocalTime.of(time.getHours(), time.getMinutes());
        foundWorkDay.setCheckOut(localTime2);
        foundWorkDay.calcDailySalary(foundWorkDay.getCheckOut());
        return saveAndReturnWorkDay(foundWorkDay);
    }



    private WorkDay saveAndReturnWorkDay (WorkDay workDay) {
        WorkDay savedWorkDay = workDayRepository.save(workDay);
        return savedWorkDay;
    }
}
