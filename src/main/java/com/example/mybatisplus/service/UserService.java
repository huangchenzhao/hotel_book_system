package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.Userorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzx
 * @since 2022-02-27
 */
public interface UserService extends IService<User>  {
    User login(User a);
    String register(HttpSession session,String code,User a);
    String sendVerification(HttpSession session, String account)throws Exception;
    String verify(HttpSession session,String account,String code);
    User showdetail(Long uid);
    void newPassword(Long uid, String newpaw);
    String forgetPassword(HttpSession request, String mail, String password,String code);
    List<User> getalluser();
    String sendMail(HttpSession session, String account);
    String addUser(User user);
    void newPass(Long uid, String pass);
    Map<String, Map<LocalDate, Float>> salesByWeek();
    String updatePrice(Long rId, Float price);
}
