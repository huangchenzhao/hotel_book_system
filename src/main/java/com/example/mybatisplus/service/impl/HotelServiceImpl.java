package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.mapper.HotelMapper;
import com.example.mybatisplus.service.HotelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

}
