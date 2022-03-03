package com.example.mybatisplus.web.controller;


import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {
    protected FileService fileService;

    protected ResourceLoader resourceLoader;

    public FileController(FileService fileService, ResourceLoader resourceLoader) {
        this.fileService = fileService;
        this.resourceLoader = resourceLoader;
    }

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> upload(MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap();
        if (SecurityUtils.getCurrentUserInfo() == null) {
            map.put("message", "请登录后重试");
            return ResponseEntity.badRequest().body(map);
        }
        HttpSession session = request.getSession();
        Long uid = (Long)session.getAttribute("uId");
        map = fileService.upload(file,uid);
        System.out.println(map);
        session.setAttribute("userPicurl",map.get("url"));
        return ResponseEntity.ok().body(map);
    }

    private static String suffix(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i == -1 ? "" : fileName.substring(i + 1);
    }

    //点击确定后将图片url写入数据库
    @GetMapping("/yesurl")
    @ResponseBody
    public JsonResponse yesurl(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long uid = (Long)session.getAttribute("uId");
        String userpicurl =(String)session.getAttribute("userPicurl");
        if (userpicurl != null){
            fileService.yesupdate(uid,userpicurl);

        }
        return JsonResponse.success(null);
    }

}
