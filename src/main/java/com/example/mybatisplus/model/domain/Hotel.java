package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@ApiModel(value="Hotel对象", description="")
public class Hotel extends Model<Hotel> {

    private static final long serialVersionUID = 1L;

    private Long hId;

    private Long aId;

    private Double longitude;

    private Double latitude;

    private Integer star;

    private String name;

    @TableField(exist=false)
    private Address address;

    @TableField(exist=false)
    private Photo photo;

    @TableField(exist=false)
    private Room room;


    @Override
    protected Serializable pkVal() {
        return this.hId;
    }

}
