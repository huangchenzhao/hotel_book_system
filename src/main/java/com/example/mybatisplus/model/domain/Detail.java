package com.example.mybatisplus.model.domain;

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
@ApiModel(value="Detail对象", description="")
public class Detail extends Model<Detail> {

    private static final long serialVersionUID = 1L;

    private LocalDate rDate;

    private Long rId;

    private Integer remain;


    @Override
    protected Serializable pkVal() {
        return this.rDate;
    }

}
