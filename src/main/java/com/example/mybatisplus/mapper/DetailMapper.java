package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Detail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
public interface DetailMapper extends BaseMapper<Detail> {
    void updateRemain(Long roomId, LocalDate checkIn, LocalDate checkOut, Integer quantity)throws Exception;
}
