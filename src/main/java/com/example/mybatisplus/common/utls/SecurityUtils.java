package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.model.domain.Admin;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityUtils {
    /**
     * 获取当前用户
     *
     * @return
     */
    public static User getCurrentUserInfo() {
        User userInfo = SessionUtils.getCurrentUserInfo();
        //模拟登录
        if (userInfo == null) {
            userInfo = new User();
            userInfo.setUsername("模拟");
        }

        return userInfo;
    }

    public static UserInfoDTO getUserInfo() {
        User userInfo = SessionUtils.getCurrentUserInfo();

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        //模拟登录
        //简单改写原函数让他不报错
        if (userInfo == null) {
            userInfo = new User();
            userInfo.setUsername("模拟用户");
            userInfoDTO.setId(1L);
            userInfoDTO.setName("模拟用户");
            userInfoDTO.setUserType(1L);
        }else{
            //调用user类的对应函数获得参数
            userInfoDTO.setId(userInfo.getuId());
            userInfoDTO.setName(userInfo.getUsername());
            userInfoDTO.setUserType(userInfo.getUsertype().longValue());
        }

        return userInfoDTO;
    }
}
