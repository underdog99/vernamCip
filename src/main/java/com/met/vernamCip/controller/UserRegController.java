/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.controller;

import com.met.vernamCip.model.User;
import com.met.vernamCip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mladen
 */

@Controller
public class UserRegController {
    
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public ModelAndView registerPost(@Valid User u, BindingResult br){
        ModelAndView mav = new ModelAndView();
        if(u == null){
            br.rejectValue("email", "error.user", "Greska korisnika!");
            
            if(br.hasErrors()){
                mav.setViewName("registration");
            }
        }else{
            userService.saveUser(u);
            mav.addObject("uspesno", "Korisnik je kreiran!");
            mav.addObject("user", new User());
            mav.setViewName("registration");
        }
        
        return mav;
    }
    

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        mav.setViewName("registration");
        return mav;
    }

    
}
