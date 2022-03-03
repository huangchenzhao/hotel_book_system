package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Userorder对象", description="")
public class Userorder extends Model<Userorder> {

    private static final long serialVersionUID = 1L;

    private Long oId;

    private Long rId;

    private Long uId;

    private Long hId;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Float totalprice;

    private Integer quantity;

    private Integer state;

    private String comment;

    @TableField(exist=false)
    private Address address;

    @TableField(exist=false)
    private Room room;

    @TableField(exist=false)
    private Detail detail;

    @TableField(exist=false)
    private Hotel hotel;

    @Override
    protected Serializable pkVal() {
        return this.oId;
    }

}
