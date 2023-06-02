/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.dao;

import com.met.vernamCip.model.Role;

/**
 *
 * @author Mladen
 */
public interface RoleDao {
    
    public Role findRoleByName(String name);
    
}
