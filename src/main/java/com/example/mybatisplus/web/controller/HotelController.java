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

    //搜索酒店
    //从前端接受key为name checkin checkout code roomtype的变量
    //返回hotel.* address.* room.price(酒店房间最低价格）photo.photo_url(酒店图片）
    //返回的数据中会统计当天酒店的所有房间剩余量，仅当房间剩余总和大于0才返回
    //如果用户输入了房型则是该房型有剩余才返回
    //酒店名称采用%hoetelname%形式的模糊搜索
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse search(@RequestParam(value="name",required=false) String hotelName, @RequestParam(value="checkin",required=false)Date checkIn,
                               @RequestParam(value="checkout",required=false) Date checkOut, @RequestParam(value="code",required=false) Integer code,
                               @RequestParam(value="roomtype",required=false)String roomType) throws Exception {
//        hotelService.save();
        List<Hotel> hotel1 =  hotelService.searchResult(hotelName,checkIn,checkOut,code,roomType);
        return JsonResponse.success(hotel1);
    }
    //酒店推荐
    //gzx
    @GetMapping("/recommend")
    @ResponseBody
    public JsonResponse recommend()throws Exception {
        List<Hotel> hotelList = hotelService.listrem();
        return JsonResponse.success(hotelList);
    }
    //酒店详情
    //gzx
    @GetMapping("/detail")
    @ResponseBody
    public JsonResponse detail(Long hId) throws Exception {
        List<Hotel> hotel = hotelService.detail(hId);
        List<Hotel> hotel2=hotelService.detail2(hId);
        for(Hotel h:hotel2){
            hotel.add(h);
        }
        return JsonResponse.success(hotel);
    }



}

