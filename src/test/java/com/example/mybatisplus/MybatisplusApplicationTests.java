package com.example.mybatisplus;

import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.mapper.UserorderMapper;
import com.example.mybatisplus.model.domain.Admin;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.model.domain.Userorder;
import com.example.mybatisplus.service.AdminService;
import com.example.mybatisplus.service.DetailService;
import com.example.mybatisplus.service.UserService;
import com.example.mybatisplus.service.UserorderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.sql.Date;
import java.time.LocalDate;
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
}
