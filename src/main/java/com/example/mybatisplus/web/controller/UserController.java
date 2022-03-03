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
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("uId", user.getuId());
        }
        return JsonResponse.success(user);
    }

    @GetMapping("/logintest")
    @ResponseBody
    public JsonResponse logintest(HttpServletRequest request, User a) {
//        User user = userService.login(a);

        HttpSession session = request.getSession();
//            session.setAttribute("",456);
        Long id = (Long) session.getAttribute("uId");
        System.out.println(id);
        return JsonResponse.success(null);
    }

    //register by hcz
    //完善了register方法，加入session并在内部调用了verify函数验证邮箱验证码
    //增加了几个不同的返回值供前端使用
    //增加需要前端传递的email，code参数
    @GetMapping("/register")
    @ResponseBody
    public JsonResponse register(HttpServletRequest request,
                                 @RequestParam(value = "email") String email,
                                 @RequestParam(value = "code") String code,
                                 @RequestParam(value="username") String username,
                                 @RequestParam(value="password") String password,
                                 @RequestParam(value="usertype") String usertype) {
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
        User a=new User();
        a.setUsername(username);
        a.setPassword(password);
        a.setUsertype(Integer.valueOf(usertype));
        System.out.println(a);
        HttpSession session = request.getSession();
        String result = userService.register(session, email, code, a);
        return JsonResponse.success(result);
    }

    //发送验证码
    //接收用户email需要xxxx.qq.com这样的全名，如果不是一个可用的邮箱则返回null
    //如果成功发出邮件则返回字符串true，否则返回null
    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse sendMessage(HttpServletRequest request, @RequestParam(value = "email", required = false) String account) throws Exception {
        HttpSession session = request.getSession();
        String result = userService.sendVerification(session, account);
        System.out.println(session.getAttribute("code"));
//      System.out.println(session.getAttribute("email"));
//      System.out.println(session.getId());
        return JsonResponse.success(result);
    }

    //邮箱验证函数，接收用户email和验证码、
    //email必须为全名且匹配接受邮件的邮箱
    //如果邮箱更改或者验证码不正确则返回null
    //验证正确无误则返回字符串true
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse verify(HttpServletRequest request, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "code", required = false) String code) {
        String str = userService.verify(request.getSession(), email, code);
//        System.out.println(session.getId());
//        System.out.println(session.getAttribute("username"));
//        Cookie cookie = new Cookie("account",name);
        HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("email"));
//        System.out.println(email);
//        System.out.println(session.getAttribute("code"));
//        System.out.println(code);
//        System.out.println(session.getId());
//        System.out.println(str);
        if (str != null) {
            return JsonResponse.success("true");
        }
        return JsonResponse.success(null);
    }


    //拿到当前登录用户的user表信息
    @GetMapping("/detail")
    @ResponseBody
    public JsonResponse details(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long uid = (Long) session.getAttribute("uId");
        return JsonResponse.success(userService.showdetail(uid));
    }



}

