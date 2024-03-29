package com.BlogPlatform.Blog.Controller;

import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.User;
import com.BlogPlatform.Blog.Model.UserSession;
import com.BlogPlatform.Blog.Service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService us;

    @PostMapping("/Users/signUp")
    public ResponseEntity<UserSession> createUser(@Valid @RequestBody User user) throws UserException {

        return new ResponseEntity<UserSession>(us.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/Users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws UserException {

        return new ResponseEntity<User>(us.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User updatedUser) throws UserException {

        return new ResponseEntity<User>(us.updateUser(id,updatedUser), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/Users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) throws UserException{

        return new ResponseEntity<User>(us.deleteUser(id), HttpStatus.OK);// due to swagger exception Failed to convert value of type 'java.lang.String' to required type 'int'. so i do change int to string;

    }

    @GetMapping("/analytics/users/{adminKey}")
    public ResponseEntity<Long> getTotalUsers(@PathVariable String adminKey) throws LogInException {

        return new ResponseEntity<Long>(us.getTotalUsers(adminKey), HttpStatus.OK);
    }

    @GetMapping("/analytics/users/top-active")
    public ResponseEntity<List<User>> getTopActiveUsers() {

        return new ResponseEntity<List<User>>(us.getTop5ActiveUsers(), HttpStatus.OK);
    }

}
