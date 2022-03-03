package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service



public class FileServiceImpl implements FileService {

    @Autowired(required=false)
    private UserMapper userMapper;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMM");

    @Value("${file-upload-path}")
    private String fileUploadPath;

    @Override
    public Map upload(MultipartFile file, Long uid) throws IOException {

        Map<String, String> map = storeFile(file,Paths.get(fileUploadPath,"image").toString());
//        String picurl = map.get("url");
//        userMapper.updatePic(uid,picurl);

        return map;
    }

    private static Map<String, String> storeFile(MultipartFile file,String fileUploadPath) throws IOException {
//        File path = null;
//        try {
//            path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(),"static/img/");
//            String yearMonth = SDF.format(new Date());//当前年月
//            String fileName = file.getOriginalFilename();//文件全名
//            File parentDir = new File(upload.getAbsolutePath()+"/" + yearMonth);
//            if(!upload.exists()){
//                upload.mkdirs();
//            }
//            if(!parentDir.exists()){
//                parentDir.mkdirs();
//            }
//            String suffix = suffix(fileName);//文件后缀
//            String relPath = "/" + yearMonth + "/" + "-" + UUID.randomUUID().toString().replaceAll("-","") + suffix;
//            File fileUp = new File(upload.getAbsolutePath()+ relPath);
//            file.transferTo(fileUp);
//            Map<String, String> map = new HashMap();
//            map.put("url", "/img" + relPath);
//            log.info(relPath);
//            return map;
//        } catch (FileNotFoundException e) {
//            throw e;
//        } catch (IOException e) {
//            throw e;
//        }
        String yearMonth=SDF.format(new Date());
        String filename=file.getOriginalFilename();
        String suffix=suffix(filename);
        String relPath="/"+yearMonth+"/"+"-"+UUID.randomUUID().toString().replaceAll("-","")+suffix;
        String toPath=fileUploadPath+relPath;
        FileOutputStream out=null;

        File toFile=new File(toPath).getParentFile();
        if (!toFile.exists()){
            toFile.mkdirs();
        }
        try{
            out=new FileOutputStream(toPath);
            out.write(file.getBytes());
            out.flush();
            Map<String,String> map=new HashMap<>();
            map.put("url","./image"+relPath);
            log.info(relPath);
            return map;
        }catch (FileNotFoundException fnfe){
            throw fnfe;
        }
    }

    /**
     * 后缀名或empty："a.png" => ".png"
     */
    private static String suffix(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i == -1 ? "" : fileName.substring(i);
    }


    @Override
    public void yesupdate(Long uid, String userpicurl) {
        userMapper.updatePic(uid,userpicurl);
    }
}
