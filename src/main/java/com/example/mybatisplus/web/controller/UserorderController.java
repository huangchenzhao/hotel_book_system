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
        session.setAttribute("roomId",roomId);
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
    //用户提交订单
    //接收前端一个参数quantity，代表用户订购房间的数量
    //根据不同情况返回前端不同的字符串
    //友情提示如果用postman测试该函数需要首先跑login，search，detail，placeOrder函数
    //后端会根据用户u_id，房间r_id,入住时间checkin，退房时间checkout判断此次订单是否已经存在数据库，如存在则返回请勿重复提交
    //后端会同时更新detail表。目前更新detail.remain字段属性为无符号，以实现防止前端出错传来错误的quantity导致数据错误，
    // 如出错返回数据库错误、
    //一切无误且更新数据库则返回预定酒店成功。
    //2022.3.4 18:51
    //添加了createtime,idcard,phonenumber,truename字段
    // 使用Userorder类封装数据，前端输入应与数据库字段匹配
    //
    @GetMapping("/submitOrder")
    @ResponseBody
    public JsonResponse submitOrder(HttpServletRequest request,Userorder userorder){
        HttpSession session = request.getSession();
        return JsonResponse.success(userorderService.submitOrder(session,userorder));
    }

    //啊哈哈哈哈，用户评价来喽！
    //这，这接口都齐了，怎么还不用啊？
    //老谷啊，这大伙都不敢用，据说，有人在接口里，写了bug！
    //害羞羞，岳大队长，你这人就喜欢开玩笑。
    //快趁热用吧，我不打扰，我走了哈！
    @GetMapping("/updatecomment")
    @ResponseBody
    public JsonResponse updatecomment(@RequestParam(value="orderId")Long orderId,@RequestParam(value="comments")String comments){
        userorderService.usercomment(orderId,comments);
        return JsonResponse.success(null);
    }



    //管理员查看某个用户的所有订单
    @GetMapping("/oneorder")
    @ResponseBody
    public  JsonResponse oneorder(Long Uid){
        List<Userorder> Oneorder = userorderService.getoneorder(Uid);
        return JsonResponse.success(Oneorder);
    }

    //管理员查看今日某个酒店房间
    //gzx
    @GetMapping("/hoteldetail")
    @ResponseBody
    public JsonResponse hoteldetail(Long hid)throws Exception{
        List<Userorder> detaillist = userorderService.showhoteldetail(hid);
        return JsonResponse.success(detaillist);
    }

    //管理员查看订单详情
    //gzx
    @GetMapping("/allorder")
    @ResponseBody
    public JsonResponse allorder()throws Exception{
        List<Userorder> orderlist = userorderService.showorderlist();
        return JsonResponse.success(orderlist);
    }


}

