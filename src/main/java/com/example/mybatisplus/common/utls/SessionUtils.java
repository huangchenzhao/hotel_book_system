package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.model.domain.Admin;
import com.example.mybatisplus.model.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    private static final String USERKEY = "sessionUser";

    //创建一个session，虽然不知道具体是怎么操作的
    public static HttpSession session() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true); // true == allow create
    }

    //获取存入的user数据的函数
    public static User getCurrentUserInfo() {
        return (User) session().getAttribute(USERKEY);
    }

    //设置user数据的函数
    //应该在login里调用
    public static void saveCurrentUserInfo(User user) {
        session().setAttribute(USERKEY, user);
    }
}
