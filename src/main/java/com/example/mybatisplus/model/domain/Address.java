package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Address对象", description="")
public class Address extends Model<Address> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long aId;

    private Integer code;

    private String province;

    private String city;

    private String district;

    private String detail;


    @Override
    protected Serializable pkVal() {
        return this.aId;
    }

}
