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

    @ApiOperation(value = "get specific workerpayment with workerpayment id", notes = "These are some notes about the API.")
    @GetMapping("/getworkerpayment/{workerpayment}")
    public ResponseEntity<WorkerPayment> getWorkerPayment(@PathVariable("workerpayment") Long workerPaymentid){
        return new ResponseEntity<WorkerPayment>(workerPaymentService.getWorkerPaymentId(workerPaymentid),
                HttpStatus.CREATED);
    }



    @ApiOperation(value = "delete workerpayment with workerpayment id", notes = "These are some notes about the API.")
    @DeleteMapping("/deleteworkerpayment/{workerpayment}")
    public ResponseEntity<WorkerPayment> delete(@PathVariable("workerpayment") Long workerpaymentid){
        return new ResponseEntity<WorkerPayment>(workerPaymentService.DeleteWorkerPayment(workerpaymentid),
                HttpStatus.CREATED);
    }


    @ApiOperation(value = "update workerpayment with workerpayment object and workerpayment id", notes = "These are some notes about the API.")
    @PostMapping("/updateworkerpayment/{workerpaymentid}")
    public ResponseEntity <WorkerPayment> updatePayments(@PathVariable Long workerpaymentid, @RequestBody WorkerPayment workerPayment) {
        WorkerPayment workerPaymentNew =workerPaymentService.updateWorkerPayment(workerpaymentid,workerPayment);
        if(workerPaymentNew == null){
            return new ResponseEntity<WorkerPayment>(workerPaymentNew,
                    HttpStatus.FORBIDDEN);
        }
        else{
            return new ResponseEntity<WorkerPayment>(workerPaymentNew,
                    HttpStatus.OK);
        }
    }
  }

