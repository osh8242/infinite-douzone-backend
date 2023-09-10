package com.douzone.rest.empphoto.controller;

import com.douzone.rest.emp.vo.Emp;
import com.douzone.rest.empphoto.service.EmpPhotoService;
import com.douzone.rest.empphoto.vo.EmpPhoto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/empPhoto")
@CrossOrigin(origins = "http://localhost:3000/")
public class EmpPhotoController {
    private static final String UPLOAD_DIRECTORY = "src/main/resources/images/";
    private EmpPhotoService empPhotoService;

    @Autowired
    public EmpPhotoController(EmpPhotoService empPhotoService) {
        this.empPhotoService = empPhotoService;
    }

    @GetMapping("/getEmpPhotoByCdEmp")
    public ResponseEntity<byte[]> getEmpPhotoByCdEmp() throws IOException {
        System.out.println("EmpPhotoController.getImage");
        //리소스 폴더에서 사진을 가져온다
        ClassPathResource imgFile = new ClassPathResource("images/osh.jpg");
        //가져온 사진파일을 바이트배열로 변환
        byte[] imageBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        //새로운 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        //헤더에 이미지 첨부
        headers.setContentType(MediaType.IMAGE_JPEG);

        //이미지 리턴
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/insertEmpPhoto")
    public ResponseEntity<String> insertEmpPhoto(@RequestParam("file") MultipartFile file,
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
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String newFilename = uuid + fileExtension;

            Path filePath = Paths.get(UPLOAD_DIRECTORY + newFilename);
            Files.write(filePath, file.getBytes());

            // 데이터베이스에 파일 정보 저장 (예: 파일 경로, 생성된 파일 이름 등)
            empPhoto.setNmPhoto(originalFilename);
            empPhoto.setFilePath(UPLOAD_DIRECTORY);
            empPhoto.setUuid(uuid);
            System.out.println("empPhoto = " + empPhoto);

            int result = empPhotoService.insertEmpPhoto(empPhoto);

            return ResponseEntity.ok("파일 업로드 성공 " + result + " : " + newFilename);
        } catch (IOException e) {
            System.out.println("파일 저장 중 에러 발생 : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 중 에러 발생");
        }
    }
}
