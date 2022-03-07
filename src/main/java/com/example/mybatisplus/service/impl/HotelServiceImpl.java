package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.mapper.AddressMapper;
import com.example.mybatisplus.mapper.RoomMapper;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.service.DetailService;
import com.example.mybatisplus.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Autowired(required = false)
    private HotelMapper hotelMapper;

    @Autowired(required = false)
    private AddressMapper addressMapper;

    @Autowired(required = false)
    private RoomMapper roomMapper;
    @Autowired(required = false)
    private DetailService detailService;

    public List<Hotel> listrem() {
        return hotelMapper.getrem();
    }

    @Override
    public List<Hotel> searchResult(String hotelName, Date checkIn, Date checkOut, Integer code, String roomType) {
        //将util.Date转化为sql.Date以匹配数据库
        java.sql.Date checkInSql = null;
        java.sql.Date checkOutSql = null;
        //处理空指针异常
        try {
            checkInSql = new java.sql.Date(checkIn.getTime());
        } catch (Exception e) {

        }
        try {
            checkOutSql = new java.sql.Date(checkOut.getTime());
        } catch (Exception e) {

        }
        //调用mapper层函数，sql语句在xml文件中实现，实现了一个又臭又长的sql
        List<Hotel> hotel = hotelMapper.searchResult(hotelName, checkInSql, checkOutSql, code, roomType);
        //返回查询数据
        return hotel;


    }

    @Override
    public List<Hotel> detail(Long hId, Date checkin, Date checkout) {
        java.sql.Date checkinSql = null;
        java.sql.Date checkoutSql = null;
        //处理空指针异常
        try {
            checkinSql = new java.sql.Date(checkin.getTime());
        } catch (Exception e) {
        }
        try {
            checkoutSql = new java.sql.Date(checkout.getTime());
        } catch (Exception e) {
        }
        List<Hotel> hoteldetail = hotelMapper.showDetails(hId, checkinSql, checkoutSql);

        return hoteldetail;
    }

    @Override
    public String saveHotelInfo(Hotelinfo hotelinfo) {
        //获得各部分数据
        try {
            Hotel hotel = hotelinfo.getHotel();
            List<Room> room = hotelinfo.getRoom();
            Address address = hotelinfo.getAddress();
            //检查地址是否存在
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("code", address.getCode());
            Address addressSearch = addressMapper.selectOne(wrapper);
            if (addressSearch != null) {
                //地址存在，填补hotel表信息
                //hotel.setAId(addressSearch.getAId());
            } else {
                //地址不存在存入数据库获取aid
                addressMapper.insert(address);
            }
            //存入aid，此时address里应有aid
            hotel.setAId(address.getAId());
            //插入hotel
            hotelMapper.insert(hotel);
            //填补并插入room表，此时hotel应有hid
            for (Room r : room) {
                r.setHId(hotel.getHId());
                roomMapper.insert(r);
                //修改detail表
                detailService.saveDetail(r.getRId(), r.getAmount());
            }
        } catch (Exception e) {
            return "添加失败";
        }
        return "成功添加酒店";
        //修改detail表
    }
//    @Override
//    public List<Hotel> detail2(Long hId) {
//        List<Hotel> hoteldetail2 = hotelMapper.showDetails2(hId);
//
//        return  hoteldetail2;
//    }
//@Override
//public List<Userorder> placeOrder(HttpSession session, Long roomId) {
//    java.sql.Date checkIn = new java.sql.Date(((java.util.Date)session.getAttribute("checkin")).getTime());
//    java.sql.Date checkOut = new java.sql.Date(((java.util.Date)session.getAttribute("checkout")).getTime());
//    Long hotelId = (Long)session.getAttribute("hId");
//    return hotelMapper.placeOrder(hotelId,roomId,checkIn,checkOut);
//}
}
