/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.met.vernamCip.convert;

/**
 *
 * @author Mladen
 */
import com.met.vernamCip.model.UserFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public class UserFileConverter {

    public static UserFile convert(MultipartFile multipartFile) throws IOException {
        UserFile userFile = new UserFile();

        userFile.setId(1000); //Set the ID as needed
        userFile.setName(multipartFile.getOriginalFilename());
        userFile.setExt(getFileExtension(multipartFile.getOriginalFilename()));
        userFile.setSize(multipartFile.getSize());
        userFile.setFullName(multipartFile.getOriginalFilename());
        userFile.setData(multipartFile.getBytes());

        return userFile;
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }
}
