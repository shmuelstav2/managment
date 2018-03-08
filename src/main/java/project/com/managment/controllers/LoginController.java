package project.com.managment.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.com.managment.domain.Role;
import project.com.managment.domain.User;
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

@Api(description = "manage all the users data")
@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }



    @ApiOperation(value = "check login operation", notes = "These are some notes about the API.")
    @PostMapping("")

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
                jo.put("status", 4);
               // jo.put("status", current.getRole());
            }
        }

        return new ResponseEntity <String>(jo.toString(),
                HttpStatus.OK);
    }


}


