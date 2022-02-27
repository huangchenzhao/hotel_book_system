package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzx
 * @since 2022-02-27
 */



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired(required=false)
    private UserMapper userMapper;

    @Override
    public User login(User a) {
        return userMapper.selectLogin(a.getUsername(),a.getPassword(),a.getUsertype());
    }
}
