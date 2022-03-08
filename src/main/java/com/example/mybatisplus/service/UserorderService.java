package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Hotel;
import com.example.mybatisplus.model.domain.Userorder;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
public interface UserorderService extends IService<Userorder> {
    List<Hotel> placeOrder(HttpSession session, Long roomId);
    List<Userorder> showorderdetail(Long uid);
    String submitOrder(HttpSession session, Userorder quantity);
//    public Userorder test(Userorder data);
    void usercomment(Long orderId, String comments);
    List<Userorder> getoneorder(Long Uid);
    List<Userorder> showhoteldetail(Long hid);
    List<Userorder> showorderlist();
}
