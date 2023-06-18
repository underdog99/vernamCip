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
import com.met.vernamCip.model.Key;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class UserKeyConverter {

    public static Key convert(MultipartFile multipartFile) throws IOException {
        Key key = new Key();

        key.setId(1000); // Set the ID as needed
        key.setName(multipartFile.getOriginalFilename());
        key.setSize(multipartFile.getSize());
        key.setContent(convertBytesToString(multipartFile.getBytes()));

        return key;
    }

    private static String convertBytesToString(byte[] bytes) {
        return new String(bytes);
    }
}
