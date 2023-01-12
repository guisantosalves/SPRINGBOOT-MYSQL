package com.springboot.api.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.api.auth.user.User;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> gettingAllUser(){
        return null;
        
    }
}
