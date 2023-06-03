/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.service;

import com.met.vernamCip.model.UserFile;
import com.met.vernamCip.model.Key;

/**
 *
 * @author Mladen
 */
public interface FileService {
    
    public UserFile getFile(long id);
    
    public void setFile(UserFile f);
    
    public void encryptFile(UserFile f, Key k);
    
    public void decryptFile(UserFile f, Key k);
    
}
