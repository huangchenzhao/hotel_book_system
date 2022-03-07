package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Detail;
import com.example.mybatisplus.mapper.DetailMapper;
import com.example.mybatisplus.service.DetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

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

    @Autowired(required=false)
    private DetailMapper detailMapper;
    @Override
    public Detail updateRemain(Long roomId, LocalDate checkIn, LocalDate checkOut,Integer quantity)throws Exception {
        detailMapper.updateRemain(roomId,checkIn,checkOut,quantity);
        return null;
    }

    @Override
    public String saveDetail(Long rId, Integer amount) {
        try {
            Calendar end = Calendar.getInstance();// 得到一个Calendar的实例
            end.setTime(new java.util.Date()); // 设置时间为当前时间
            end.add(Calendar.MONTH, +1);// 设置为一个月后
            //end.add(Calendar.DATE, +1);// 日期加1
            java.util.Date result = end.getTime();
            Calendar begin = Calendar.getInstance();//获得当前时间
            Long startTime = begin.getTimeInMillis();
            Long endTime = end.getTimeInMillis();
            Long oneDay = 1000 * 60 * 60 * 24l;// 一天的时间转化为ms
            Long time = startTime;
            //插入往后一个月的数据
            while (time <= endTime) {
                java.util.Date d = new java.util.Date(time);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.format(d);
                System.out.println(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                Detail detail = new Detail(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), rId, amount);
                detailMapper.insert(detail);
                time += oneDay;
            }
        } catch (Exception e) {
            return "deteail更新失败";
        }
        return null;
    }
}
