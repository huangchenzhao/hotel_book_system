package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Detail;
import com.example.mybatisplus.mapper.DetailMapper;
import com.example.mybatisplus.service.DetailService;
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
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements DetailService {

}
