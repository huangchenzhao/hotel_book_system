package com.example.mybatisplus.model.domain;

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
@ApiModel(value="Room对象", description="")
public class Room extends Model<Room> {

    private static final long serialVersionUID = 1L;

    private Long rId;

    private Long hId;

    private Float price;

    private String roomtype;

    private Integer amount;

    private Integer maxPeople;


    @Override
    protected Serializable pkVal() {
        return this.rId;
    }

}
