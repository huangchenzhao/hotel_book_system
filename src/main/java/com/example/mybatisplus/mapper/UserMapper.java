package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzx
 * @since 2022-02-27
 */
public interface UserMapper extends BaseMapper<User> {
    User selectLogin(String username, String password, Integer usertype);

    void updatePic(Long uid, String picurl);
}
