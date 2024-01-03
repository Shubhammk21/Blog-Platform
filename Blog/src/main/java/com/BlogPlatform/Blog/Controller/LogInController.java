package com.BlogPlatform.Blog.Controller;

import com.BlogPlatform.Blog.Dto.LogInDto;
import com.BlogPlatform.Blog.Exception.LogInException;
import com.BlogPlatform.Blog.Exception.UserException;
import com.BlogPlatform.Blog.Model.UserSession;
import com.BlogPlatform.Blog.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogInController {

    @Autowired
    private LogInService login;

    @PostMapping("User/Login")
    public ResponseEntity<UserSession> logInCustomer(@RequestBody LogInDto dto) throws UserException, LogInException {

        UserSession user= login.logInUser(dto);

        return new ResponseEntity<UserSession>(user, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("User/LogOut")
    public ResponseEntity<String> logOutCustomer(@RequestParam(required = false) String key) throws LogInException{
        String str= login.logOutUser(key);

        return new ResponseEntity<String>(str, HttpStatus.OK);
    }
}
