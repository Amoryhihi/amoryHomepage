package com.amory.controller;

import com.amory.domain.ResponseResult;
import com.amory.utils.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam(value = "file",required = false)MultipartFile file) throws Exception {
        String url = FileUtil.uploads(file);
        return ResponseResult.okResult(url);
    }
}
