/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.service;

import com.met.vernamCip.model.File;
import com.met.vernamCip.model.Key;

/**
 *
 * @author Mladen
 */
public interface FileService {
    
    public File getFile(long id);
    
    public void setFile(File f);
    
    public void encryptFile(File f, Key k);
    
    public void decryptFile(File f, Key k);
    
}
