package com.example.mybatisplus;

import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.model.domain.Admin;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.service.AdminService;
import com.example.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.*;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Admin byId = adminService.getById(1);
        System.out.println(byId);
    }
    @Autowired(required=false)
    private HotelMapper hotelMapper;

    @Test
    void test(){
        long l = Date.parse("1 Feb 2022");
        long ll = Date.parse("3 Feb 2022");
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
        List<Hotel> abc =hotelMapper.showDetails(1L,checkinSql,checkoutSql);
        System.out.println(abc);
    }

//    @Test
//     void test(){
//        List<Hotel> abc =hotelMapper.showDetails(1L);
//        System.out.println(abc);
//    }
//    @Test
//    void test1()throws Exception{
//        userService.sendVerification("2468038761");
//    }
}
