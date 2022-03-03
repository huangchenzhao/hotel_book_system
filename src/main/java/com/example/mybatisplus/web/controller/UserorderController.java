package com.example.mybatisplus.web.controller;

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
@RequestMapping("/userorder")
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

    //显示当前用户所有订单
    //gzx
    @GetMapping("/orderdetail")
    @ResponseBody
    public JsonResponse orderdetails(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long uid = (Long) session.getAttribute("uId");
        return JsonResponse.success(userorderService.showorderdetail(uid));
    }
}

