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
    Userorder selectId(Long uid);
     List<Userorder> placeOrder(Long hotelId, Long roomId, Date checkIn, Date checkOut);
}
