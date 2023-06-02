package com.stu.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AliOssService {

    void createBucket();
    String upload(MultipartFile file);
    void download(String fileName) throws IOException;
    void listFile();
    void deleteFile(String fileName);


}
