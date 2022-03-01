package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Autowired(required=false)
    private HotelMapper hotelMapper;

    public List<Hotel> listrem() {
        return hotelMapper.getrem();
    }

    @Override
    public List<Hotel> searchResult(String hotelName, Date checkIn, Date checkOut, Integer code, String roomType) {
        //将util.Date转化为sql.Date以匹配数据库
        java.sql.Date checkInSql=null;
        java.sql.Date checkOutSql=null;
        //处理空指针异常
        try{
            checkInSql= new java.sql.Date(checkIn.getTime());
        }catch(Exception e){

        }
        try{
            checkOutSql= new java.sql.Date(checkOut.getTime());
        }catch(Exception e){

        }
        //调用mapper层函数，sql语句在xml文件中实现，实现了一个又臭又长的sql
        List<Hotel> hotel = hotelMapper.searchResult(hotelName, checkInSql, checkOutSql, code, roomType);
        //返回查询数据
        return hotel;
    }


}
