package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Hotelinfo;
import com.example.mybatisplus.service.HotelService;
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

    @Autowired(required = false)
    private HotelService hotelService;

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
    //2022.3.4
    //修复了同一邮箱多次注册的bug
    //改变了参数接口。email参数和用户表字段匹配为mail
    //前端需将email转成mail
    @GetMapping("/register")
    @ResponseBody
    public JsonResponse register(HttpServletRequest request, @RequestParam(value = "code", required = false) String code, User a) {
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
        HttpSession session = request.getSession();
        String result = userService.register(session, code, a);
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
    public JsonResponse verify(HttpServletRequest request, @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "code", required = false) String code) {
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
            return JsonResponse.success("验证成功");
        }
        return JsonResponse.success("邮箱或验证码错误");
    }


    //拿到当前登录用户的user表信息
    @GetMapping("/detail")
    @ResponseBody
    public JsonResponse details(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long uid = (Long) session.getAttribute("uId");
        return JsonResponse.success(userService.showdetail(uid));
    }



    //修改密码
    //用户需输入原密码与新密码
    //若原密码不对，则返回提示
    //原密码与新密码一致，返回提示
    //新密码为null或""，返回提示
    @GetMapping("/newpassword")
    @ResponseBody
    public JsonResponse newpassword(HttpServletRequest request,String oldpaw,String newpaw){
        HttpSession session = request.getSession();
        Long uid = (Long) session.getAttribute("uId");
        User user = userService.getById(uid);
        System.out.println(user.getPassword());
        if (oldpaw.equals(user.getPassword()) ){
            if (oldpaw.equals(newpaw)){
                return JsonResponse.failure("旧密码与新密码一致，请换一个新密码");
            }
            else {
                if (newpaw == null ||newpaw.isEmpty()){
                    return JsonResponse.failure("新密码不得为null或空字符串，请换一个新密码");
                }
                else {
                    userService.newPassword(uid,newpaw);
                    return JsonResponse.success("修改密码成功");
                }
            }
        }
        else{
            return JsonResponse.failure("旧密码与原密码不一致，请重新输入");
        }
    }



    //找回密码两个接口
    //发送邮件调用sendMailForPassword,接收一个email参数，具体实现调用了注册的sendEmail实现函数,如果觉得太长了可以删掉一点
    //如果用户输入的邮箱不存在于数据库则返回邮箱不存在
    //更新密码调用forgetPassword，接收email和password和code参数,如果邮箱无误且验证码验证成功
    // 则录入数据库，并返回找回成功，否则返回邮箱或验证码错误
    @RequestMapping(value = "/sendMailForPassword", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse sendMailToGetPassword(HttpServletRequest request,
                                              @RequestParam(value = "email", required = false) String account) throws Exception {
        HttpSession session = request.getSession();

        String result = userService.sendMail(session, account);

        return JsonResponse.success(result);
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse forgetPassword(HttpServletRequest request,@RequestParam(value="email",required=false)String mail,
                                       @RequestParam(value="code",required=false)String code,
                                       @RequestParam(value="password",required=false)String password){
        return JsonResponse.success(userService.forgetPassword(request.getSession(),mail,password,code));
    }

    //管理员看到user表的数据
    //
    @GetMapping("/alluser")
    @ResponseBody
    public JsonResponse alluser(){
        List<User> allusers = userService.getalluser();
        return JsonResponse.success(allusers);
    }

    //添加酒店，接收前端酒店信息，地址信息，房间信息，图片信息，如果酒店已存在数据库则返回酒店已注册，否则写入数据库
    //具体传参格式参考我在群里传的图片
    @PostMapping("/addHotel")
    @ResponseBody
    public JsonResponse addHotel(@RequestBody Hotelinfo hotelinfo){
        System.out.println(hotelinfo);
        return JsonResponse.success(hotelService.saveHotelInfo(hotelinfo));
    }

    //移除酒店
    //移除后用户不可搜索到该酒店
    //但用户订单信息中的被删除酒店信息仍旧存在
    @GetMapping("/removeHotel")
    @ResponseBody
    public JsonResponse removeHotel(@RequestParam(value = "h_id", required = false) Long hId) {
        return JsonResponse.success(hotelService.removeById(hId));
    }
    //管理员修改密码
    //
    @GetMapping("/newpass")
    @ResponseBody
    public JsonResponse newpass(Long uid,String pass){
        userService.newPass(uid,pass);
        return JsonResponse.success("成功");
    }


    //移除用户
    //移除后用户不可登录
    //注册功能未修改，意味着移除用户后用户可通过自己的邮箱重新注册一个账号，即使重新用户名和原本的一模一样
    //数据库会添加一条数据
    //然后用户可以正常登录
    @GetMapping("/removeUser")
    @ResponseBody
    public JsonResponse removeUser(@RequestParam(value = "u_id", required = false) Long uId) {
        return JsonResponse.success(userService.removeById(uId));
    }

    //添加用户
    //如果用户名和邮箱在数据库中有唯一匹配的数据则解除逻辑删除且更新密码
    //如果没有唯一匹配的数据则查询用户名和邮箱是否已被使用（从没有被逻辑删除的用户中找）
    //被使用则返回对应数据被使用
    //否则添加进数据库并返回成功
    @GetMapping("/addUser")
    @ResponseBody
    public JsonResponse addUser(User user) {
        System.out.println(userService.addUser(user));
        return JsonResponse.success(null);
    }
    @GetMapping("/salesByWeek")
    @ResponseBody
    public JsonResponse salesByWeek(){
        return JsonResponse.success(userService.salesByWeek());
    }
    @GetMapping("/updatePrice")
    @ResponseBody
    public JsonResponse updatePrice(Long rId,Float price){
        return JsonResponse.success(userService.updatePrice(rId,price));
    }
}

