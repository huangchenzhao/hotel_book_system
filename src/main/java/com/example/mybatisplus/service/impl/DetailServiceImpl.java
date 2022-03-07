package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Detail;
import com.example.mybatisplus.mapper.DetailMapper;
import com.example.mybatisplus.service.DetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        java.util.Date date = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格式
        String dateStr = dateFormat.format( date );
        System.out.println(dateStr);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        //插入一个月的房间数据
        //还没写完
        for(java.util.Date date1=new java.util.Date();;){
            break;
        }
        return null;
    }
}
