package com.jw.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OssService {
    String uploadeFileAvatar(MultipartFile file) throws IOException;
}
