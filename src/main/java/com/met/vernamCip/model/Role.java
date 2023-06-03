/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Mladen
 */

@Entity
@Table(name = "role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRole")
    private int id;

    @Column(name = "nameRole")
    private String role;
    
    @Column(name = "actionRole")
    private int action;
    
    @Column(name = "maxSizeFileRole")
    private float maxSizeFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public float getMaxSizeFile() {
        return maxSizeFile;
    }

    public void setMaxSizeFile(float maxSizeFile) {
        this.maxSizeFile = maxSizeFile;
    }
    
    
    
    
}
