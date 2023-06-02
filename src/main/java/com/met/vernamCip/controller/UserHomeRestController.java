/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.controller;

import com.met.vernamCip.model.User;
import com.met.vernamCip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mladen
 */

@RestController
public class UserHomeRestController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/user/home")
    public String homePage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(auth.getName());
        
        return u.getEmail();
    }
}
