package com.example.mybatisplus.service;


import com.example.mybatisplus.model.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileService {

    Map upload(MultipartFile file, Long uid) throws IOException;
    void yesupdate(Long uid, String userpicurl);
}
