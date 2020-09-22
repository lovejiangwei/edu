package com.jw.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodVideoService {
    String uploadVideo(MultipartFile file);

    void deleteVideoById(String videoId);
}
