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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**************************************************************\
 *
 *          API for authentication
 *
 *
 *************************************************************/

//Api for authentication

@Api(description = "login api")
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
        User current = userService.getUserByIdNumber(user.getIdNumber());
        JSONObject jo = new JSONObject();
        if(current == null){
            jo.put("status", 5);
        }
        else {
            if (current.getId() == null) {
                // if the user doesnt exist return 5
                jo.put("status", 5);
            } else {
                if (!current.getPassword().equals(user.getPassword())) {
                    //if the use exist but the passo 4
                    jo.put("status", 4);
                } else {
                    jo.put("status", current.getRole());
                    jo.put("id", current.getId());
                }
            }
        }
        return new ResponseEntity <String>(jo.toString(),
                HttpStatus.OK);
     }
}


