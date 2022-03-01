package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.HotelService;
import com.example.mybatisplus.model.domain.Hotel;

import java.util.Date;
import java.util.List;


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
@RequestMapping("/hotel")
public class HotelController {

    private final Logger logger = LoggerFactory.getLogger( HotelController.class );

    @Autowired
    private HotelService hotelService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Hotel  hotel =  hotelService.getById(id);
        return JsonResponse.success(hotel);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        hotelService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public JsonResponse updateHotel(@PathVariable("id") Long  id,Hotel  hotel) throws Exception {
//        hotel.setId(id);
//        hotelService.updateById(hotel);
//        return JsonResponse.success(null);
//    }


    /**
    * 描述:创建Hotel
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Hotel  hotel) throws Exception {
        hotelService.save(hotel);
        return JsonResponse.success(null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse create(@RequestParam(value="name") String hotelName, @RequestParam(value="checkin")Date checkIn,
                               @RequestParam(value="checkout") Date checkOut, @RequestParam(value="roomtype")String roomType) throws Exception {
//        hotelService.save();
        List<Hotel> hotel =  hotelService.searchResult(hotelName,checkIn,checkOut,roomType);
        return JsonResponse.success(hotel);
    }
    //酒店推荐
    //gzx
    @GetMapping("/recommend")
    @ResponseBody
    public JsonResponse recommend()throws Exception {
        List<Hotel> hotelList = hotelService.listrem();
        return JsonResponse.success(hotelList);
    }
}

