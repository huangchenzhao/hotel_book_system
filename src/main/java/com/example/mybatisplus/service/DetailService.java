package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Detail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
public interface DetailService extends IService<Detail> {
    Detail updateRemain(Long roomId, LocalDate checkIn, LocalDate checkOut, Integer quantity)throws Exception;
    String saveDetail(Long rId, Integer amount);
}
