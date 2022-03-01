package com.example.mybatisplus;

import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.model.domain.Admin;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private AdminService adminService;
    @Test
    void contextLoads() {
        Admin byId = adminService.getById(1);
        System.out.println(byId);
    }
    @Autowired(required=false)
    private HotelMapper hotelMapper;

    @Test
     void test(){
        Hotel hotel=new Hotel();
        for(int i=0;i<100;i++)
        {

        }
    }
}
