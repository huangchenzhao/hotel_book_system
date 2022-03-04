package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.model.domain.Userorder;
import com.example.mybatisplus.mapper.UserorderMapper;
import com.example.mybatisplus.service.UserorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.sql.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
@Service
public class UserorderServiceImpl extends ServiceImpl<UserorderMapper, Userorder> implements UserorderService {
    @Autowired(required=false)
    private UserorderMapper userorderMapper;
    @Override
    public Userorder showorderdetail(Long uid){
        Userorder currorder = userorderMapper.selectId(uid);
        System.out.println(currorder);
        System.out.println(currorder);
        return currorder;
    }

    @Override
    public Userorder submitOrder(HttpSession session, int quantity) {
        Long roomId = (Long)session.getAttribute("roomId");
        Userorder userorder = userorderMapper.selectRoom(roomId);
        userorderMapper.submitOrder(quantity,roomId);
        return null;
    }

    @Autowired(required=false)
    HotelMapper hotelMapper;
    @Override
    public List<Hotel> placeOrder(HttpSession session, Long roomId) {
        Date checkIn = new Date(((java.util.Date)session.getAttribute("checkin")).getTime());
        Date checkOut = new Date(((java.util.Date)session.getAttribute("checkout")).getTime());
        Long hotelId = (Long)session.getAttribute("hotelId");
        session.setAttribute("roomId",roomId);
        System.out.println(hotelId);
        System.out.println(roomId);
        List<Hotel> result = hotelMapper.placeOrder(hotelId,roomId,checkIn,checkOut);
        for(Hotel h:result){
            h.setCheckIn(checkIn);
            h.setCheckOut(checkOut);
        }
        return result;
    }
}
