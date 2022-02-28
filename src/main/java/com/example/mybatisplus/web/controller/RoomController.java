package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.RoomService;
import com.example.mybatisplus.model.domain.Room;


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
@RequestMapping("/room")
public class RoomController {

    private final Logger logger = LoggerFactory.getLogger( RoomController.class );

    @Autowired
    private RoomService roomService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Room  room =  roomService.getById(id);
        return JsonResponse.success(room);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        roomService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public JsonResponse updateRoom(@PathVariable("id") Long  id,Room  room) throws Exception {
//        room.setId(id);
//        roomService.updateById(room);
//        return JsonResponse.success(null);
//    }


    /**
    * 描述:创建Room
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Room  room) throws Exception {
        roomService.save(room);
        return JsonResponse.success(null);
    }
}

