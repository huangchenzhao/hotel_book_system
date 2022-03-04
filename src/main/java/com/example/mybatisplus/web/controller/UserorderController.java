package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.model.domain.Hotel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserorderService;
import com.example.mybatisplus.model.domain.Userorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/api/userorder")
public class UserorderController {

    private final Logger logger = LoggerFactory.getLogger( UserorderController.class );

    @Autowired
    private UserorderService userorderService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Userorder  userorder =  userorderService.getById(id);
        return JsonResponse.success(userorder);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userorderService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public JsonResponse updateUserorder(@PathVariable("id") Long  id,Userorder  userorder) throws Exception {
//        userorder.setId(id);
//        userorderService.updateById(userorder);
//        return JsonResponse.success(null);
//    }


    /**
    * 描述:创建Userorder
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Userorder  userorder) throws Exception {
        userorderService.save(userorder);
        return JsonResponse.success(null);
    }

    //用户下单
    //接收前端一个roomid
    //返回前端对应时间段内该房间最少剩余量，酒店名称，房型，详细地址等信息
    //入住时间和退房时间分别存入checkIn和checkOut
    @RequestMapping(value="/placeOrder",method=RequestMethod.GET)
    @ResponseBody
    public JsonResponse placeOrder(HttpServletRequest request, @RequestParam(value="roomId", required=false)Long roomId){
        HttpSession session = request.getSession();
        List<Hotel> result = userorderService.placeOrder(session,roomId);
        return JsonResponse.success(result);
    }


    //显示当前用户所有订单
    //gzx
    @GetMapping("/orderdetail")
    @ResponseBody
    public JsonResponse orderdetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long uid = (Long) session.getAttribute("uId");
        return JsonResponse.success(userorderService.showorderdetail(uid));
    }
    @GetMapping("/submitOrder")
    @ResponseBody
    public JsonResponse submitOrder(HttpServletRequest request,@RequestParam(value="quantity",required=false)int quantity){
        HttpSession session = request.getSession();
        userorderService.submitOrder(session,quantity);
        return JsonResponse.success(null);
    }

    //啊哈哈哈哈，用户评价来喽！
    //这，这接口都齐了，怎么还不用啊？
    @GetMapping("/updatecomment")
    @ResponseBody
    public JsonResponse updatecomment(@RequestParam(value="orderId")Long orderId,@RequestParam(value="comments")String comments){
        userorderService.usercomment(orderId,comments);
        return JsonResponse.success(null);
    }

}

