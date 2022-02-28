package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.PhotoService;
import com.example.mybatisplus.model.domain.Photo;


/**
 *
 *  前端控制器
 *
 *
 * @author gzx
 * @since 2022-02-28
 * @version v1.0
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {

    private final Logger logger = LoggerFactory.getLogger( PhotoController.class );

    @Autowired
    private PhotoService photoService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Photo  photo =  photoService.getById(id);
        return JsonResponse.success(photo);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        photoService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public JsonResponse updatePhoto(@PathVariable("id") Long  id,Photo  photo) throws Exception {
//        photo.setId(id);
//        photoService.updateById(photo);
//        return JsonResponse.success(null);
//    }


    /**
    * 描述:创建Photo
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Photo  photo) throws Exception {
        photoService.save(photo);
        return JsonResponse.success(null);
    }
}

