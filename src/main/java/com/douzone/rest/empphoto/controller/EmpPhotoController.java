package com.douzone.rest.empphoto.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/empPhoto")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpPhotoController {
    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage() throws IOException {
        System.out.println("EmpPhotoController.getImage");
        // resources/images 폴더에서 이미지를 로드합니다.
        ClassPathResource imgFile = new ClassPathResource("images/osh.jpg");
        byte[] imageBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
