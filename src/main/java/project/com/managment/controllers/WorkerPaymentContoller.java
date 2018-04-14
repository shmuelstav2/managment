package project.com.managment.controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.managment.domain.Time;
import project.com.managment.domain.WorkDay;
import project.com.managment.services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

        import com.cloudinary.Cloudinary;
                import com.cloudinary.utils.ObjectUtils;
                import com.fasterxml.jackson.databind.annotation.JsonSerialize;
                import io.swagger.annotations.Api;
                import io.swagger.annotations.ApiOperation;
                import org.json.JSONObject;
                import org.springframework.format.annotation.DateTimeFormat;
                import org.springframework.http.*;
                import org.springframework.stereotype.Controller;
                import org.springframework.web.bind.annotation.*;
                import org.springframework.web.multipart.MultipartFile;
                import project.com.managment.domain.*;
import project.com.managment.services.WorkDayService;

import java.io.File;
                import java.io.FileOutputStream;
                import java.io.IOException;
                import java.nio.file.FileSystems;
                import java.nio.file.Files;
                import java.nio.file.Path;
                import java.nio.file.Paths;
                import java.text.ParseException;
                import java.text.SimpleDateFormat;
                import java.time.LocalDate;
                import java.time.format.DateTimeFormatter;
                import java.util.*;
                import org.springframework.http.HttpStatus;
                import org.springframework.http.ResponseEntity;
                import org.springframework.stereotype.Controller;
                import org.springframework.web.bind.annotation.GetMapping;
                import org.springframework.web.bind.annotation.PathVariable;
                import org.springframework.web.bind.annotation.PostMapping;
                import org.springframework.web.bind.annotation.RequestMapping;
                import java.util.List;

@Api(description = "manage all the work days data")
@Controller
@RequestMapping("/api/workerpayments/")
public class WorkerPaymentContoller {
    private final WorkerPaymentService workerPaymentService;

    public WorkerPaymentContoller (WorkerPaymentService workerPaymentService) {
        this.workerPaymentService = workerPaymentService;
    }

    @ApiOperation(value = "This will get a list of all the worker payments.", notes = "These are some notes about the API.")
    @GetMapping("/workerpayments")
    public ResponseEntity<List<WorkerPayment>> getallWorkerPayments(){
        List <WorkerPayment> workerPayments = workerPaymentService.getAllWorkerPayments();
        return new ResponseEntity<List<WorkerPayment>>(workerPayments, HttpStatus.OK);
    }



    @ApiOperation(value = "create new WorkerPayment", notes = "These are some notes about the API.")
    @PostMapping("/{workerid}/{userid}")
    public ResponseEntity<WorkerPayment> createNewWorkDay(@PathVariable("workerid") Long workerid, @PathVariable("userid") Long userid, @RequestBody WorkerPayment workerPayment){
        return new ResponseEntity<WorkerPayment>(workerPaymentService.createNewWorkerPayment(workerid,userid,workerPayment),
                HttpStatus.CREATED);
    }
 /*
    @ApiOperation(value = "add new check in", notes = "These are some notes about the API.")
    @PostMapping("/addcheckin/{projectid}/{userid}")
    public ResponseEntity<WorkDay> addCheckIn(@PathVariable("projectid") Long projectid,@PathVariable("userid") Long userid){
        return new ResponseEntity<WorkDay>(workDayService.addCheckIn(projectid,userid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "add new checkout", notes = "These are some notes about the API.")
    @PostMapping("/addcheckout/{workdayid}")
    public ResponseEntity<WorkDay> addCheckIn(@PathVariable("workdayid") Long workdayid){
        return new ResponseEntity<WorkDay>(workDayService.addCheckOut(workdayid),
                HttpStatus.CREATED);
    }


    @ApiOperation(value = "get specific workday with workday id", notes = "These are some notes about the API.")
    @GetMapping("/getworkday/{workdayid}")
    public ResponseEntity<WorkDay> getWorkDay(@PathVariable("workdayid") Long workdayid){
        return new ResponseEntity<WorkDay>(workDayService.getWorkDayId(workdayid),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete workday with workday id", notes = "These are some notes about the API.")
    @DeleteMapping("/deleteworkdaycheckout/{workdayid}")
    public ResponseEntity<WorkDay> delete(@PathVariable("workdayid") Long workdayid){
        return new ResponseEntity<WorkDay>(workDayService.DeleteWorkDayId(workdayid),
                HttpStatus.CREATED);
    }



    @ApiOperation(value = "update workday check out with workday object and workday id", notes = "These are some notes about the API.")
    @PostMapping("/updateworkdaycheckout/{workdayid}")
    public ResponseEntity<WorkDay> updateWorkDayCheckOut(@PathVariable("workdayid") Long workdayid,@RequestBody Time time){
        return new ResponseEntity<WorkDay>(workDayService.updateWorkDayCheckOut(workdayid,time),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "update workday with workday object and workday id", notes = "These are some notes about the API.")
    @PostMapping("/updateworkdaycheckin/{workdayid}")
    public ResponseEntity<WorkDay> updateWorkDayCheckIn(@PathVariable("workdayid") Long workdayid,@RequestBody Time time){
        return new ResponseEntity<WorkDay>(workDayService.updateWorkDayCheckIn(workdayid,time),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "update workday with date and workday id", notes = "These are some notes about the API.")
    @PostMapping("/updateworkdaydate/{workdayid}")
    public ResponseEntity<WorkDay> updateWorkDayCheckIn(@PathVariable("workdayid") Long workdayid,@RequestBody String dateString){
        JSONObject jsonObject = new JSONObject(dateString);
        if(jsonObject.has("year")) {
            int year = jsonObject.getInt("year");
            int month = jsonObject.getInt("month");
            int day = jsonObject.getInt("date");
            Date date = new Date(year-1900,month,day);
            return new ResponseEntity<WorkDay>(workDayService.updateWorkDayDate(workdayid,date),
                    HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
        }

    }


    @JsonSerialize
    public class EmptyJsonResponse { }

    @ApiOperation(value = "get workday with id", notes = "These are some notes about the API.")
    @GetMapping("/getWorkDay/{userid}")
    public ResponseEntity<WorkDay> getWorkDay(@PathVariable("userid") Long userid ,@RequestParam("date")  String fromDate ){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );

        try {

            LocalDate newdate =LocalDate.parse(fromDate, formatter);;
            System.out.print("this is:  "+newdate+"       ");
            return new ResponseEntity<WorkDay>(workDayService.getWorkDay(userid,newdate),
                    HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //look for workdays
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    */
}

