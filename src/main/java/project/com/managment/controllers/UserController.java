package project.com.managment.controllers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import project.com.managment.domain.User;
import project.com.managment.services.UserService;

import java.util.Collection;
import java.util.List;

@Api(description = "manage all the users data")
@Controller
@RequestMapping("/api/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "This will get a list of users.", notes = "These are some notes about the API.")
    @GetMapping
    public ResponseEntity<List<User>> getallUser(){
       List <User> users = userService.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @ApiOperation(value = "create new user", notes = "These are some notes about the API.")
    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.createNewUser(user),
                HttpStatus.CREATED);
    }
    @ApiOperation(value = "get user by id number", notes = "These are some notes about the API.")
    @GetMapping({"/{id}"})
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }


   /* @GetMapping (params = "firstName")
    public Iterable<User> findByFirstName(
            @Spec(path = "firstName", spec = Like.class) Specification<User> spec) {
        return userService.findAll(spec);
    }*/
}
