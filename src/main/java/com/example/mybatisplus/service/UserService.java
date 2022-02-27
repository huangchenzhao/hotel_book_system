package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzx
 * @since 2022-02-27
 */
public interface UserService extends IService<User> {
    User login(User a);
}
