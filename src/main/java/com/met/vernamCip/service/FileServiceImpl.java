/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.service;

import com.met.vernamCip.model.UserFile;
import com.met.vernamCip.model.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mladen
 */


@Service
public class FileServiceImpl implements FileService {
    
    private static final int BUFF_SIZE = 8192;
    
    @Override
    public UserFile getFile(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFile(UserFile f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void encryptFile(UserFile f, Key k) {
        try{
            System.out.println("Pocetak enkripcije...");
            FileInputStream fis = new FileInputStream(f.getFullName());
            FileOutputStream fos;
            
            if(f.getFullName().endsWith(".vcEncrypt")){
                f.setFullName(f.getFullName().replace(".vcEncrypt", ""));
                fos = new FileOutputStream("decrypt_"+f.getFullName());
            }else{
                fos = new FileOutputStream(f.getFullName() + ".vcEncrypt");
            }
            
            byte[] buff = new byte[BUFF_SIZE];
            int byteRead;
            
            float keySize = k.getSize();
            int keyIndex = 0;
            
            while ((byteRead = fis.read(buff)) != -1){
                for(int i = 0; i < byteRead; i ++){
                    buff[i] = (byte) (buff[i] ^ k.getContent().charAt(keyIndex));
                    keyIndex = (int) ((keyIndex + 1) % keySize);
                }
                
                fos.write(buff, 0, byteRead);
            }
            
            fis.close();
            fos.close();
            System.out.println("Datoteka je enkriptovana!");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void decryptFile(UserFile f, Key k) {
        System.out.println("Pocetak dekripcije...");
        encryptFile(f, k);
        System.out.println("Datoteka je dekriptovana!");
    }
    
}
