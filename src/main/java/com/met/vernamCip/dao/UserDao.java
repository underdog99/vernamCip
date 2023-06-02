/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.dao;

import com.met.vernamCip.model.User;

/**
 *
 * @author Mladen
 */
public interface UserDao {
    
    public User getUser(int id);
    
    public User findUserByEmail(String email);
    
    public void saveUser(User u);
    
}
