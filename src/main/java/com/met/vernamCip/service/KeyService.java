/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.service;

import com.met.vernamCip.model.Key;

/**
 *
 * @author Mladen
 */
public interface KeyService {

    public Key getKey(long id);

    public void setKey(Key k);

}
