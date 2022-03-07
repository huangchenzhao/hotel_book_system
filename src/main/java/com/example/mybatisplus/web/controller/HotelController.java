package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.model.domain.Hotelinfo;
import com.example.mybatisplus.model.domain.Room;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.HotelService;
import com.example.mybatisplus.model.domain.Hotel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;


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
@RequestMapping("/api/hotel")
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
    //酒店名称采用%hotelname%形式的模糊搜索
    //2022.3.3
    //调整了sql逻辑，接收参数不变，返回值为hotel.* address.*
    // room.price(酒店所有房间最低价格，如果用户选了房型则是对应房型的最低价格)photo.photo_url(酒店图片）
    //detail.remain(酒店从用户输入的起始时间到退房时间的所有房型的房间剩余最低量的总和，仅当大于0返回
    //上面这句话比较难理解，做如下解释：
    //首先找出酒店的所有房型，其次找到从入住时间到退房时间这段时间内该房型剩余量最少的数量，此数量作为该房型的最小剩余量
    //最后将酒店不同房型的最小剩余量相加，作为酒店房间剩余量返回
    //如果用户输入了房型则只计算对应房型
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse search(HttpServletRequest request, @RequestParam(value="name",required=false) String hotelName, @RequestParam(value="checkin",required=false)Date checkIn,
                               @RequestParam(value="checkout",required=false) Date checkOut, @RequestParam(value="code",required=false) Integer code,
                               @RequestParam(value="roomtype",required=false)String roomType) throws Exception {
//        hotelService.save();
        HttpSession session = request.getSession();
        session.setAttribute("checkin",checkIn);
        session.setAttribute("checkout",checkOut);
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
    @PostMapping("/test")
    @ResponseBody
    public JsonResponse test(@RequestBody Hotelinfo hotelinfo){
       System.out.println(hotelinfo);
       hotelService.saveHotelInfo(hotelinfo);
        return JsonResponse.success(hotelinfo);
    }
    //酒店详情
    //gzx
    @GetMapping("/detail")
    @ResponseBody
    public JsonResponse detail(HttpServletRequest request, Long hId) throws Exception {
        HttpSession session = request.getSession();
        Date checkin = (Date) session.getAttribute("checkin");
        Date checkout = (Date) session.getAttribute("checkout");
        session.setAttribute("hotelId",hId);
        List<Hotel> hotel = hotelService.detail(hId,checkin,checkout);
//        List<Hotel> hotel2=hotelService.detail2(hId);
//        for(Hotel h:hotel2){
//            hotel.add(h);
//        }
        return JsonResponse.success(hotel);
    }



}

