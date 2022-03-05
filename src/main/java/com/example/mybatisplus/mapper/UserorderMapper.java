package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Userorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
public interface UserorderMapper extends BaseMapper<Userorder> {
    List<Userorder> selectId(Long uid);
    void usercomment(Long orderId, String comments);
     List<Userorder> placeOrder(Long hotelId, Long roomId, Date checkIn, Date checkOut);
     void submitOrder(int quantity, Long roomId);
     Userorder selectRoom(Long roomId);
     List<Userorder> getoneorder(Long Uid);
}
