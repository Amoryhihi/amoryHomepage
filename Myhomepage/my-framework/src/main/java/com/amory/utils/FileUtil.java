package com.amory.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {
    public static final String UPLOADS_PATH = "F:\\amoryHomepage\\img-avatar\\";
    public static String uploads(MultipartFile file) throws IOException {
        final String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);
        String fileName = UUID.randomUUID().toString().replace("-","")+"."+fileSuffix;
        File descFile = new File(UPLOADS_PATH,fileName);
        file.transferTo(descFile);

        String url = "http://localhost:7777/upload/"+fileName;
        return url;
    }
}
