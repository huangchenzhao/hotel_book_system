package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.Hotel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;



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
public interface HotelMapper extends BaseMapper<Hotel> {

    List<Hotel> searchResult(String hotelName, Date checkIn, Date checkOut, String roomType);
    List<Hotel> getrem();
}
