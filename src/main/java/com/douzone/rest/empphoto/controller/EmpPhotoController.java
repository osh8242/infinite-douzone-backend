package com.douzone.rest.empphoto.controller;

import com.douzone.rest.empphoto.service.EmpPhotoService;
import com.douzone.rest.empphoto.vo.EmpPhoto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/empPhoto")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpPhotoController {
    private static final String UPLOAD_DIRECTORY = "src/main/resources/images/";
    private final EmpPhotoService empPhotoService;

    @Autowired
    public EmpPhotoController(EmpPhotoService empPhotoService) {
        this.empPhotoService = empPhotoService;
    }

    @GetMapping("/getEmpPhotoByCdEmp")
    public ResponseEntity<byte[]> getEmpPhotoByCdEmp(@RequestParam("cdEmp") String cdEmp) throws IOException {
        System.out.println("EmpPhotoController.getEmpPhotoByCdEmp");

        EmpPhoto empPhoto = empPhotoService.getEmpPhotoByCdEmp(cdEmp);
        System.out.println("empPhoto = " + empPhoto);

        if (empPhoto.getNmPhoto() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("해당 직원의 사진 정보를 찾을 수 없습니다.".getBytes(StandardCharsets.UTF_8));
        }

        byte[] imageBytes = null;
        Path path = null;
        String filePath = null;
        HttpHeaders headers = null;

        try {
            // 조회된 사진 정보를 사용하여 사진 파일의 전체 경로를 구성
            filePath = UPLOAD_DIRECTORY + empPhoto.getUuid() + "." + empPhoto.getNmPhoto().split("\\.")[1];
            path = Paths.get(filePath);
            // 사진 파일을 로드
            imageBytes = Files.readAllBytes(path);
            //새로운 헤더 설정
            //headers = new HttpHeaders();
            //헤더에 이미지 첨부
            //headers.setContentType(MediaType.IMAGE_JPEG);
        } catch (IOException e) {
            filePath = UPLOAD_DIRECTORY + "defaultProfile.jpg";
            path = Paths.get(filePath);
            // 사진 파일을 로드
            imageBytes = Files.readAllBytes(path);
            return new ResponseEntity<>(imageBytes, HttpStatus.OK);
        }

        //이미지 리턴
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

    @PutMapping("/updateEmpPhoto")
    public ResponseEntity<String> updateEmpPhoto(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("fileExtension") String fileExtension,
                                                 @RequestParam("pkValue") String pkValueJsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("EmpPhotoController.insertEmpPhoto");
        EmpPhoto empPhoto = null;
        try {
            empPhoto = objectMapper.readValue(pkValueJsonString, EmpPhoto.class);
            System.out.println(empPhoto.toString());
        } catch (JsonProcessingException e) {
            System.out.println("objectMapper 에러 발생 : " + e);
        }

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("업로드할 파일이 없습니다.");
        }

        try {
            // UUID 생성하여 파일명 결정
            //originalFilename = 4조.jpg
            String originalFilename = file.getOriginalFilename();
            //FileExtension = .jpg

            //uuid = ec41c727-f1de-43c4-a5c7-41182e581eee
            String uuid = UUID.randomUUID().toString();
            //newFilename = ec41c727-f1de-43c4-a5c7-41182e581eee.jpg
            String newFilename = uuid + "." + fileExtension;

            //filePath = src\main\resources\images\ec41c727-f1de-43c4-a5c7-41182e581eee.jpg
            Path filePath = Paths.get(UPLOAD_DIRECTORY + newFilename);
            Files.write(filePath, file.getBytes());

            // 데이터베이스에 파일 정보 저장 (예: 파일 경로, 생성된 파일 이름 등)
            empPhoto.setNmPhoto(originalFilename);
            empPhoto.setFilePath(UPLOAD_DIRECTORY);
            empPhoto.setUuid(uuid);
            System.out.println("empPhoto = " + empPhoto);

            int result = empPhotoService.updateEmpPhoto(empPhoto);

            return ResponseEntity.ok("파일 업로드 성공 " + result + " : " + newFilename);
        } catch (IOException e) {
            System.out.println("파일 저장 중 에러 발생 : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 중 에러 발생");
        }
    }
}
