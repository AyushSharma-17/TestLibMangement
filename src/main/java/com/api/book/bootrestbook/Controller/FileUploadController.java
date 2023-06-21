package com.api.book.bootrestbook.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
       System.out.println(file.getOriginalFilename());
       System.out.println(file.getSize());
       System.out.println(file.getContentType());
       System.out.println(file.getContentType());
       System.out.println(file.getName());
         
       //validation
       if(file.isEmpty()){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request not found");
       }
       
       return ResponseEntity.ok("working");
    }
}
