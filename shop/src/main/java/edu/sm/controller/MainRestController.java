package edu.sm.controller;

import edu.sm.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MainRestController {

    @Value("${file.upload-dir}")
    private String uploadImgDir;

    @Value("${file.upload-dir-audio}")
    private String uploadAudioDir;

    @RequestMapping("/saveaudio")
    public Object saveaudio(@RequestParam("file") MultipartFile file) throws IOException {
        FileUploadUtil.saveFile(file, uploadAudioDir);
        return "OK";
    }
    @RequestMapping("/saveimg")
    public Object saveimg(@RequestParam("file") MultipartFile file) throws IOException {
        FileUploadUtil.saveFile(file, uploadImgDir);
        return "OK";
    }
}