package com.example.File.Upload.controllers;

import com.example.File.Upload.services.BasicService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class BasicController {

    @Autowired
    private BasicService basicService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return basicService.upload(file);
    }

    @GetMapping("/download")
    public byte[] download(@RequestParam String fileName, HttpServletResponse response) throws Exception {
        String ex = FilenameUtils.getExtension(fileName);
        switch (ex) {
            case "png" -> response.setContentType(MediaType.IMAGE_PNG_VALUE);
            case "jpg", "jpeg" -> response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            case "gif" -> response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
        return basicService.download(fileName);
    }
}
