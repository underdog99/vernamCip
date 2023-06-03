/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.controller;

import com.met.vernamCip.model.Key;
import com.met.vernamCip.model.UserFile;
import com.met.vernamCip.service.FileService;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mladen
 */
@RestController
public class UserFileRestController {

    @Autowired
    private FileService fileService;

    @PostMapping("/user/home/fileEncrypt")
    public ResponseEntity<UserFile> getEncrypt(@RequestBody UserFile uf, @RequestBody Key k) {

        /*
        Test
        ---------------------------------
        
        File f = new File("testPDF.pdf");

        Key k = new Key();

        UserFile uf = new UserFile();
        uf.setId(10000);
        uf.setName("testPDF");
        uf.setExt(".pdf.vcEncrypt");
        //uf.setExt(".pdf");
        uf.setFullName(uf.getName() + uf.getExt());
        uf.setSize(f.length());

        while (uf.getSize() > k.getSize()) {
            k.setContent(k.getContent() + "2");
            k.setSize(k.getContent().length());

        }
        
        System.out.println("Fajl size: " + uf.getSize());
        System.out.println("Key size: " + k.getSize());
        */

        fileService.encryptFile(uf, k);
        
        return new ResponseEntity(uf, HttpStatus.OK);
    }
    
    @PostMapping("/user/home/fileDecrypt")
    public ResponseEntity<UserFile> getDecrypt(@RequestBody UserFile uf, @RequestBody Key k){
        
        fileService.decryptFile(uf, k);
        
        return new ResponseEntity(uf, HttpStatus.OK);
        
    }

}
