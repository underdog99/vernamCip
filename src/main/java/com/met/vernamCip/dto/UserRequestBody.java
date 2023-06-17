/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.dto;

import com.met.vernamCip.model.Key;
import com.met.vernamCip.model.UserFile;

/**
 *
 * @author Mladen
 */
public class UserRequestBody {
    
    private UserFile uf;
    private Key k;

    public UserFile getUf() {
        return uf;
    }

    public void setUf(UserFile uf) {
        this.uf = uf;
    }

    public Key getK() {
        return k;
    }

    public void setK(Key k) {
        this.k = k;
    }

    
}
