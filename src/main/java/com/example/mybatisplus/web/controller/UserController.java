package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.model.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 前端控制器
 *
 * @author gzx
 * @version v1.0
 * @since 2022-02-27
 *
 *
 *
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 描述：根据Id 查询
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id) throws Exception {
        User user = userService.getById(id);
        return JsonResponse.success(user);
    }

    /**
     * 描述：根据Id删除
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        userService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
     * 描述：根据Id 更新
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateUser(@PathVariable("id") Long id, User user) throws Exception {
        userService.updateById(user);
        return JsonResponse.success(null);
    }


    /**
     * 描述:创建User
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(User user) throws Exception {
        userService.save(user);
        return JsonResponse.success(null);
    }

    //登录
//    @GetMapping("/login/{username}/{password}/{radio}")
//    @ResponseBody
//    public JsonResponse login(@PathVariable("username") String username, @PathVariable("password") String password, @PathVariable("radio") Integer usertype) throws Exception {
//        User user = userMapper.selectLogin(username,password,usertype);
//        return JsonResponse.success(user);
//    }
    ///test
    @GetMapping("/login")
    @ResponseBody
    public JsonResponse login(HttpServletRequest request, User a) {
        User user = userService.login(a);
        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("uId",user.getuId());
        }
        return JsonResponse.success(user);
    }


    //register by hcz
    @GetMapping("/register")
    @ResponseBody
    public JsonResponse register(User a) {
//        List<User> userList = userService.list();
//        int flag = 0;  //标记变量，如果为1说明user表里存在用户要注册的用户名
//        for (User tempUser : userList) {
//            if (tempUser.getUsername().equals(a.getUsername())) {
//                flag = 1;
//            }
//        }
//        if (flag==1) {
//            return JsonResponse.success(null);  //如果存在该用户名则不存入数据库，返回null
//        } else {
//            userService.save(a); //如果不存在该用户名则注册成功，写入数据库
//            return JsonResponse.success(a);
//        }
        return JsonResponse.success(userService.register(a));
    }
}

