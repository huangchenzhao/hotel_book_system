package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Photo;
import com.example.mybatisplus.mapper.PhotoMapper;
import com.example.mybatisplus.service.PhotoService;
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
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

}
