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
    private EmpPhotoService empPhotoService;

    @Autowired
    public EmpPhotoController(EmpPhotoService empPhotoService) {
        this.empPhotoService = empPhotoService;
    }

    @GetMapping("/getEmpPhoto")
    public ResponseEntity<byte[]> getEmpPhoto() throws IOException {
        System.out.println("EmpPhotoController.getEmpPhoto");
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

    @GetMapping("/getEmpPhotoByCdEmp")
    public ResponseEntity<byte[]> getEmpPhotoByCdEmp(@RequestParam("cdEmp") String cdEmp) throws IOException {
        System.out.println("EmpPhotoController.getEmpPhotoByCdEmp");

        EmpPhoto empPhoto = empPhotoService.getEmpPhotoByCdEmp(cdEmp);
        System.out.println("empPhoto = " + empPhoto);

        if (empPhoto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("해당 직원의 사진 정보를 찾을 수 없습니다.".getBytes(StandardCharsets.UTF_8));
        }

        // 조회된 사진 정보를 사용하여 사진 파일의 전체 경로를 구성
        String filePath = UPLOAD_DIRECTORY + empPhoto.getUuid() + "." + empPhoto.getNmPhoto().split("\\.")[1];
        System.out.println("filePath = " + filePath);
        // 사진 파일을 로드
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("사진 파일을 찾을 수 없습니다.".getBytes(StandardCharsets.UTF_8));
        }
        byte[] imageBytes = Files.readAllBytes(path);
        //새로운 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        //헤더에 이미지 첨부
        headers.setContentType(MediaType.IMAGE_JPEG);

        //이미지 리턴
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @PutMapping("/updateEmpPhoto")
    public ResponseEntity<String> updateEmpPhoto(@RequestParam("file") MultipartFile file,
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
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //uuid = ec41c727-f1de-43c4-a5c7-41182e581eee
            String uuid = UUID.randomUUID().toString();
            //newFilename = ec41c727-f1de-43c4-a5c7-41182e581eee.jpg
            String newFilename = uuid + fileExtension;

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
            //originalFilename = 4조.jpg
            String originalFilename = file.getOriginalFilename();
            //FileExtension = .jpg
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //uuid = ec41c727-f1de-43c4-a5c7-41182e581eee
            String uuid = UUID.randomUUID().toString();
            //newFilename = ec41c727-f1de-43c4-a5c7-41182e581eee.jpg
            String newFilename = uuid + fileExtension;

            //filePath = src\main\resources\images\ec41c727-f1de-43c4-a5c7-41182e581eee.jpg
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
