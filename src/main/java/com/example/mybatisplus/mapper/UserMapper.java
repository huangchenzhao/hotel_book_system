package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.List;

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
    void newPassword(Long uid, String newpaw);
    List<User> getalluser();
    void updatePassword(String mail,String password)throws Exception ;
    Boolean isExist(String mail);
    Boolean updateUser(User user);
    void newPass(Long uid, String pass);
}
