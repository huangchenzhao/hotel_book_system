/*
package com.example.mybatisplus;

import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.mapper.UserorderMapper;
import com.example.mybatisplus.model.domain.Admin;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.domain.Userorder;
import com.example.mybatisplus.service.AdminService;
import com.example.mybatisplus.service.DetailService;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.service.UserorderService;
import com.example.mybatisplus.web.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.*;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserorderService userorderService;

    @Autowired(required =false)
    private UserorderMapper userorderMapper;

    @Test
    void contextLoads() {
        Admin byId = adminService.getById(1);
        System.out.println(byId);
    }
    @Autowired(required=false)
    private HotelMapper hotelMapper;

    @Autowired(required=false)
    private UserController userController;

    @Test
    void test(){
        long l = Date.parse("28 Feb 2022");
        long ll = Date.parse("4 Mar 2022");
        Date date1= new Date(l);
        Date date2= new Date(ll);
        java.sql.Date checkinSql=null;
        java.sql.Date checkoutSql=null;
        //处理空指针异常
        try{
            checkinSql= new java.sql.Date(date1.getTime());
        }catch(Exception e){
        }
        try{
            checkoutSql= new java.sql.Date(date2.getTime());
        }catch(Exception e) {
        }
        List<Hotel> abc =hotelMapper.showDetails(null,checkinSql,checkoutSql);
        List<Hotel> result = hotelMapper.test(abc);
        System.out.println(abc);
        System.out.println(result);
    }

//    @Test
//     void test(){
//        List<Hotel> abc =hotelMapper.showDetails(1L);
//        System.out.println(abc);
//    }
//    @Test
//    void test1()throws Exception{
//
//        List<Userorder> userorder=hotelMapper.placeOrder(1L,1L,new Date(2022-1900,2-1,28),new Date(2022-1900,3-1,4));
//        System.out.println(userorder);
//    }
    @Test
    void test1()throws Exception{

        System.out.println(userorderMapper.selectRoom(1L));
    }
    @Autowired(required=false)
    private DetailService detailService;
    @Test
    void test3()throws Exception{
//        Userorder data = new Userorder();
////        data.setQuantity(2);
////        data.setCheckIn((LocalDate.of(2022,2,28)));
////        data.setCheckOut((LocalDate.of(2022,3,4)));
////        data.setuId(1L);
//        LocalDate date1 = LocalDate.of(2022,2,28);
//        LocalDate date2 = LocalDate.of(2022,3,4);
//        try{System.out.println(detailService.updateRemain(1L,date1,date2,8));}
//        catch(Exception e){
//            System.out.println("异常");
//        }
    }
    @Test
    void test4()throws Exception{
        System.out.println(userController.removeHotel(1L));
    }
    @Test
    void test5 (){
        User user = new User();
        user.setUsername("张iahfha");
        user.setPassword("456");
        user.setMail("123gag@qq.com");
        System.out.println(userController.addUser(user));
        //System.out.println(userController.alluser());
    }
    */
/*@Test
    void test6(){
        //插入房间
        List<String> room=new ArrayList<>();
        room.add("标准间");
        room.add("大床房");
        room.add("双人间");
        room.add("亲子房");
        room.add("总统套房");

        List<String> hotel=new ArrayList<>();
        hotel.add("如家");
        hotel.add("七天");

        for(Long i=1L;i<10L;i++){
            for(Long j=1L;j<5L;j++){

            }
        }
    }*//*

}
*/
