package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
