/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.service;

import com.met.vernamCip.dao.RoleDao;
import com.met.vernamCip.dao.UserDao;
import com.met.vernamCip.model.Role;
import com.met.vernamCip.model.User;
import java.util.Arrays;
import java.util.HashSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mladen
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public User getUser(int u) {
        return userDao.getUser(u);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    @Transactional
    public String saveUser(User u) {
        User uEmailExist = userDao.findUserByEmail(u.getEmail());
        System.out.println("Pocetak operacije za upor.");

        if (uEmailExist != null) {
            System.out.println("Email postoj!");
        } else {
            System.out.println("Unutar metode saveUser()");
            u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
            u.setAc(true);
            Role r = roleDao.findRoleByName("FREE");
            System.out.println("Rola: " + r.getRole());
            u.setRoles(new HashSet<Role>(Arrays.asList(r)));
            System.out.println("Pozivanje userDao.saveUser()");
            userDao.saveUser(u);
        }

        return "Token";
    }

}
