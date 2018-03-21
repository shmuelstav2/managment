package project.com.managment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

import javax.persistence.*;

        import com.fasterxml.jackson.annotation.JsonBackReference;
        import com.fasterxml.jackson.annotation.JsonManagedReference;
        import lombok.*;

        import javax.persistence.*;
        import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;
import java.util.List;

import org.hibernate.annotations.Fetch;
        import org.hibernate.annotations.FetchMode;

import static java.time.temporal.ChronoUnit.MINUTES;

//Data create getters seteers and constructor
@Data
@Entity
@Table(name = "WorkDays",uniqueConstraints={@UniqueConstraint(columnNames = {"id"})})
public class WorkDay {
    private static final int DAYTIME=240;
    //Uniq id with autogeneration
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonBackReference(value="project-movement")
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonBackReference(value="user-movement")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;
    private String comment;
    private LocalTime checkIn;
    private LocalTime  checkOut;
    private double totalDailyAmount;
  /*
    projectId Project
  */
 public void calcDailySalary(LocalTime checkOutTime){
     double salary =0;
     if(checkOut==null){
         checkOut = checkOutTime;
     }
     Long minutes = getPeriod(checkIn,checkOut);
     if (minutes>DAYTIME){
         salary = salary + this.getUser().getPayDay();
         minutes = minutes -270;
         if (minutes>0){
             salary = salary + (this.getUser().getAdditionHourPay()*(minutes/60));
         }
     }
     else{

     }
     this.setTotalDailyAmount(salary);
 }
    private static Long getPeriod(LocalTime dob, LocalTime now) {
        return MINUTES.between(dob, now);
    }
}