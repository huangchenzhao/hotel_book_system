package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDate;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author gzx
 * @since 2022-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Userorder对象", description = "")
public class Userorder extends Model<Userorder> {

    private static final long serialVersionUID = 1L;

    private Long oId;

    private Long rId;

    private Long uId;

    private Long hId;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private String truename;

    private String idcard;

    private String phonenumber;

    private Date createdTime;

    public Userorder(Userorder a) {
        try {
            this.comment = "暂无评论";
            this.quantity = a.getQuantity();
            this.state = 0;
            this.createdTime = new Date();
            this.phonenumber = a.getPhonenumber();
            this.truename = a.getTruename();
            this.idcard = a.getIdcard();
        } catch (Exception e) {

        }

    }
    public Userorder() {
    }

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long gethId() {
        return hId;
    }

    public void sethId(Long hId) {
        this.hId = hId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private Float totalprice;

    private Integer quantity;

    private Integer state;

    private String comment;

    @TableField(exist = false)
    private Address address;

    @TableField(exist = false)
    private Room room;

    @TableField(exist = false)
    private Detail detail;

    @TableField(exist = false)
    private Hotel hotel;


    @Override
    protected Serializable pkVal() {
        return this.oId;
    }

}
