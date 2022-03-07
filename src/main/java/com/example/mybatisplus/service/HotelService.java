package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.Hotelinfo;
import com.example.mybatisplus.model.domain.Userorder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
public interface HotelService extends IService<Hotel> {
    List<Hotel> listrem();
    List<Hotel> searchResult(String hotelName, Date checkIN, Date checkOut, Integer code, String roomType);
    List<Hotel> detail(Long hId, Date checkin, Date checkout);
    String saveHotelInfo(Hotelinfo hotelinfo);
    List<Hotel> showlist();
//    List<Userorder> placeOrder(HttpSession session, Long roomId);
//    List<Hotel> detail2(Long hId);
}
