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

@Api(description = "manage all the users data")
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

    /*  @ApiOperation(value = "get user by id number", notes = "These are some notes about the API.")
      @GetMapping({"/{id}"})
      public ResponseEntity<User> getUserById(@PathVariable Long id){
          return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
      }
  */
    /*
    @ApiOperation(value = "get user by id of identication", notes = "These are some notes about the API.")
    @GetMapping({"/{idnumber}"})
    public ResponseEntity<User> getUserByIdNumber(@PathVariable("idnumber") Long idNumber){
        return new ResponseEntity<User>(userService.getUserByIdNumber(idNumber), HttpStatus.OK);
    }


    @ApiOperation(value = "get user by Role", notes = "These are some notes about the API.")
    @GetMapping({"/role/{role}"})
    public ResponseEntity<List<User>> getUserById(@PathVariable Role role){
        return new ResponseEntity<List<User>>(userService.getUsersByRole(role), HttpStatus.OK);
    }

    @ApiOperation(value = "check login operation", notes = "These are some notes about the API.")
    @PostMapping("/login")

    public ResponseEntity <String> checkUserLogin(@RequestBody User user){
        //User current = userService.getUserByIdNumber(user.getIdNumber());
        User current =new User();
        JSONObject jo = new JSONObject();
        if(current.getId()== null){
            // if the user doesnt exist return 5
            jo.put("status", 5);
        }else {
            if(!current.getPassword().equals(user.getPassword())){
                //if the use exist but the passo 4
                jo.put("status", 4);
            }else{
                // and if both are true return the role number
                jo.put("status", current.getRole());
            }
        }

        return new ResponseEntity <String>(jo.toString(),
                HttpStatus.OK);
    }


    @ApiOperation(value = "update the user by the idNumber", notes = "Please be carefill provide only the new data")
    @PutMapping({"/{idNumber}"})
    public ResponseEntity <User> updateUser(@PathVariable Long idNumber, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(idNumber, user),
                HttpStatus.OK);
    }
    //    return new ResponseEntity <String>("huiyhui",
    //          HttpStatus.OK);
}*/


