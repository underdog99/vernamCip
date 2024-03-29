/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.controller;

import com.met.vernamCip.conf.FileHandler;
import com.met.vernamCip.convert.UserFileConverter;
import com.met.vernamCip.convert.UserKeyConverter;
import com.met.vernamCip.dto.UserRequestBody;
import com.met.vernamCip.model.Key;
import com.met.vernamCip.model.Role;
import com.met.vernamCip.model.User;
import com.met.vernamCip.model.UserFile;
import com.met.vernamCip.service.FileService;
import com.met.vernamCip.service.UserService;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Mladen
 */
@RestController
public class UserFileRestController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileHandler fileHandler;
    

    @PostMapping("/user/home/fileEncrypt")
    public ResponseEntity<?> getEncrypt(@RequestPart("file") MultipartFile file, @RequestPart("key") MultipartFile key) {
         System.out.println("----file enc------");
    UserFile uf = null;
    Key k = null;
    System.out.println("----------");
    System.out.println(file);
    System.out.println("----------");
    System.out.println(key);
    System.out.println("----------");
    try {
        uf = UserFileConverter.convert(file);
        k = UserKeyConverter.convert(key);
        System.out.println("---- uf ------");
        System.out.println(uf);
        System.out.println("----- k -----");
        System.out.println(k);

    } catch (IOException ex) {
        Logger.getLogger(UserFileRestController.class.getName()).log(Level.SEVERE, null, ex);
    }

    fileService.setFile(uf); //Kreiranje i cuvanje fajla

    // Declare encryptedFile variable
    UserFile encryptedFile = null;

    //---- Autentifikacija i autorizacija korisnika, provera role/paket usluge------
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // User u = userService.findUserByEmail(auth.getName());

    // Set<Role> roles = u.getRoles();
    // Role role = null;

    // for (Role r : roles) {
    //     if (r.getRole().equals("FREE") || r.getRole().equals("STANDARD") || r.getRole().equals("PRO")) {
    //         role = r;
    //     }
    // }

    // System.out.println("Rola: " + role.getRole() + " Max akcije: " + role.getAction());
    //------------------------------------------------------------------------------------

    //float maxFile = role.getMaxSizeFile();          //Max. velicina fajla iz odabranog paket usluge
    //fileHandler.setMaxAction(role.getAction());     //setovanje max. akcije u istom vremenu

    //float maxFile = 1000000000;          //Max. velicina fajla iz odabranog paket usluge
    fileHandler.setMaxAction(10); //setovanje max. akcije u istom vremenu

    // Provera velicine fajla
    // if (maxFile <= uf.getSize()) {
    // Provera trenutnog brojaca akcija
    if (fileHandler.canPerformAction()) {
        fileHandler.setCurrAction(fileHandler.getCurrAction() + 1);
        encryptedFile = fileService.encryptFile(uf, k); // Start enkripcije metode
        System.out.println("CurrAction: " + fileHandler.getCurrAction());
    } else {
        return new ResponseEntity("U toku je maximalan broj akcija!", HttpStatus.TOO_MANY_REQUESTS);
    }
    fileHandler.setCurrAction(fileHandler.getCurrAction() - 1);
    System.out.println("CurrAction: " + fileHandler.getCurrAction());

if (encryptedFile != null) {
    return ResponseEntity.ok(encryptedFile);
} else {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nastala je greska prilikom enkripcije fajla.");
}

    // } else {
    //     return new ResponseEntity("Prekoracena max. velicina fajla!", HttpStatus.FORBIDDEN);
    // }
    }

    @PostMapping("/user/home/fileDecrypt")
    public ResponseEntity<?> getDecrypt(@RequestPart("file") MultipartFile file, @RequestPart("key") MultipartFile key) {

        UserFile uf = null;
        Key k = null;
        try {
            uf = UserFileConverter.convert(file);
            k = UserKeyConverter.convert(key);
        } catch (IOException ex) {
            Logger.getLogger(UserFileRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //---- Autentifikacija i autorizacija korisnika, provera role/paket usluge------
      
        fileService.setFile(uf); //Kreiranje i cuvanje fajla

         UserFile decryptedFile = null;
        //-------------------------------------------------------------------------------------
        
        // float maxFile = role.getMaxSizeFile();
        fileHandler.setMaxAction(10);


            if (fileHandler.canPerformAction()) {
                fileHandler.setCurrAction(fileHandler.getCurrAction() + 1);
                decryptedFile = fileService.decryptFile(uf, k);
                System.out.println("CurrAction: " + fileHandler.getCurrAction());
            } else {
                return new ResponseEntity("U toku je maximalan broj akcija!", HttpStatus.TOO_MANY_REQUESTS);
            }
            fileHandler.setCurrAction(fileHandler.getCurrAction() - 1);
            System.out.println("CurrAction: " + fileHandler.getCurrAction());
           // return new ResponseEntity(uf, HttpStatus.OK);
         
            if (decryptedFile != null) {
                return ResponseEntity.ok(decryptedFile);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nastala je greska prilikom dekripcije fajla.");
            }

    }

}
