package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<Hotel> searchResult(String hotelName, Date checkIn, Date checkOut, String roomType) {
        java.sql.Date checkInSql= new java.sql.Date(checkIn.getTime());
        java.sql.Date checkOutSql= new java.sql.Date(checkOut.getTime());
        List<Hotel> hotel = hotelMapper.searchResult(hotelName, checkInSql, checkOutSql, roomType);
        return hotel;
    }
}
