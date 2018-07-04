package project.com.managment.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.managment.domain.*;
import project.com.managment.services.PaymentsService;
import project.com.managment.services.UserService;

import java.util.List;

import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.json.JSONObject;
        import org.springframework.data.jpa.domain.Specification;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import project.com.managment.domain.Role;
        import project.com.managment.domain.User;
        import project.com.managment.services.UserService;

        import java.util.Collection;
        import java.util.List;
import java.util.Set;

@Api(description = "manage all the Payments data")
@Controller
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @ApiOperation(value = "This will get a list of all the payments.", notes = "These are some notes about the API.")
    @GetMapping
    public ResponseEntity<Set<Payments>> getallPayments() {
        Set<Payments> payments = paymentsService.getAllPayments();
        return new ResponseEntity<Set<Payments>>(payments, HttpStatus.OK);
    }

    @ApiOperation(value = "create new Payment", notes = "These are some notes about the API.")
    @PostMapping("/{projectid}")
    public ResponseEntity<Payments> createNewPayments(@PathVariable("projectid") Long projectid,@RequestBody Payments payments) {
        Payments payment = paymentsService.createNewPayments(projectid, payments);
        if (payment == null) {
            return new ResponseEntity<Payments>(payment,
                    HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<Payments>(payment,
                    HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "get payments by id number", notes = "These are some notes about the API.")
    @GetMapping({"/{id}"})
      public ResponseEntity<Payments> getPaymentsById(@PathVariable Long id){
          return new ResponseEntity<Payments>(paymentsService.paymentsById(id), HttpStatus.OK);
      }

    @ApiOperation(value = "update the payments by  id ", notes = "Please be carefill provide only the new data")
    @PutMapping({"/{id}/{projectid}"})
    public ResponseEntity <Payments> updatePayments(@PathVariable Long id,@PathVariable Long projectid, @RequestBody Payments payments) {
        Payments payment = paymentsService.updatePayments(id,projectid, payments);
        if(payment == null){
            return new ResponseEntity<Payments>(payment,
                    HttpStatus.FORBIDDEN);
        }
        else{
            return new ResponseEntity<Payments>(payment,
                    HttpStatus.OK);
        }

    }
}


