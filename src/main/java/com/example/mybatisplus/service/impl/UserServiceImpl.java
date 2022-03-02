package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*wrapper.lambda().eq(User::getPassword,a.getPassword())
                .eq(User::getUsername,a.getUsername())
                .eq(User::getUsertype,a.getUsertype());*/
        wrapper.eq("username",a.getUsername())
                .eq("password", a.getPassword())
                .eq("usertype",a.getUsertype());
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User register(User a) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.lambda().eq(User::getUsername,a.getUsername());
        wrapper.eq("username",a.getUsername());
        User result = userMapper.selectOne(wrapper);
        if(result!=null){//存在用户则返回空
            return null;
        }else{//不存在则录入返回登陆信息
            userMapper.insert(a);
            return a;
        }
    }

    @Override
    public User showdetail(Long uid){
        User curruser = userMapper.selectById(uid);
        System.out.println(curruser);
        System.out.println(curruser);
        return curruser;
    }
}
