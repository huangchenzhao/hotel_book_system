package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Userorder;
import com.example.mybatisplus.mapper.UserorderMapper;
import com.example.mybatisplus.service.UserorderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserorderServiceImpl extends ServiceImpl<UserorderMapper, Userorder> implements UserorderService {
    @Autowired(required=false)
    private UserorderMapper userorderMapper;
    @Override
    public Userorder showorderdetail(Long uid){
        Userorder currorder = userorderMapper.selectId(uid);
        System.out.println(currorder);
        System.out.println(currorder);
        return currorder;
    }

}
