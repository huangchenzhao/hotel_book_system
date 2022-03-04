package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.model.domain.Userorder;
import com.example.mybatisplus.mapper.UserorderMapper;
import com.example.mybatisplus.service.DetailService;
import com.example.mybatisplus.service.UserorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.sql.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
@Service
public class UserorderServiceImpl extends ServiceImpl<UserorderMapper, Userorder> implements UserorderService {
    @Autowired(required = false)
    private UserorderMapper userorderMapper;

    @Autowired(required = false)
    private DetailService detailService;

    @Override
    public Userorder showorderdetail(Long uid) {
        Userorder currorder = userorderMapper.selectId(uid);
        System.out.println(currorder);
        System.out.println(currorder);
        return currorder;
    }

    @Override
    public String submitOrder(HttpSession session, int quantity) {
        Long roomId = (Long) session.getAttribute("roomId");
        Userorder userorder = null;
        try {
            userorder = userorderMapper.selectRoom(roomId);
        } catch (Exception e) {
            return "房间信息未找到！";
        }
        Userorder data = new Userorder();
        try {
            data.sethId(userorder.getRoom().getHId());
            data.setCheckIn(((java.util.Date) session.getAttribute("checkin")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            data.setCheckOut(((java.util.Date) session.getAttribute("checkout")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            data.setrId(roomId);
            data.setuId((Long) session.getAttribute("uId"));
            data.setQuantity(quantity);
            data.setTotalprice(quantity * userorder.getRoom().getPrice());
            data.setState(0);
            data.setComment("暂无评论");
        } catch (Exception e) {
            return "用户数据出错！";
        }
        if (test(data) != null) {
            return "请勿重复提交同一订单！";
        }
        try {
            System.out.println("data:");
            System.out.println(data);
            try {
                detailService.updateRemain(roomId, data.getCheckIn(), data.getCheckOut(),quantity);
            } catch (Exception e) {
                return "数据库出错！请稍后重试。";
            }
            userorderMapper.insert(data);
        } catch (Exception e) {
            return "订单提交失败！请刷新后重试。";
        }
        return "预定酒店成功！";
    }

    private Userorder test(Userorder data) {
        QueryWrapper<Userorder> wrapper = new QueryWrapper<>();
        wrapper.eq("u_id", data.getuId())
                .eq("r_id", data.getrId()).eq("check_in", data.getCheckIn())
                .eq("check_out", data.getCheckOut());
        return userorderMapper.selectOne(wrapper);
    }

    @Autowired(required = false)
    HotelMapper hotelMapper;

    @Override
    public List<Hotel> placeOrder(HttpSession session, Long roomId) {
        if (roomId == null) {
            return null;
        }
        Date checkIn = new Date(((java.util.Date) session.getAttribute("checkin")).getTime());
        Date checkOut = new Date(((java.util.Date) session.getAttribute("checkout")).getTime());
        Long hotelId = (Long) session.getAttribute("hotelId");
        System.out.println(hotelId);
        System.out.println(roomId);
        List<Hotel> result = hotelMapper.placeOrder(hotelId, roomId, checkIn, checkOut);
        for (Hotel h : result) {
            h.setCheckIn(checkIn);
            h.setCheckOut(checkOut);
        }
        return result;
    }

    @Override
    public void usercomment(Long orderId, String comments){
        userorderMapper.usercomment(orderId,comments);
    }
}
