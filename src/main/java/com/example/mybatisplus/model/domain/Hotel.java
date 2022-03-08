package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.sql.Date;

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
@ApiModel(value="Hotel对象", description="")
public class Hotel extends Model<Hotel> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long hId;

    private Long aId;

    private Double longitude;

    private Double latitude;

    private Integer star;

    private String name;

    @TableLogic
    @TableField("is_deleted")
    private Boolean deleted;

    @TableField(exist=false)
    private Address address;

    @TableField(exist=false)
    private Photo photo;

    @TableField(exist=false)
    private Room room;

    @TableField(exist=false)
    private Userorder userOrder;

    @TableField(exist=false)
    private Detail detail;

    @TableField(exist=false)
    private Date checkIn;

    @TableField(exist=false)
    private Date checkOut;

    @TableField(exist = false)
    private Integer countt;



    @Override
    protected Serializable pkVal() {
        return this.hId;
    }

}
