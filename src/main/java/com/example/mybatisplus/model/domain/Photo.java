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
@ApiModel(value = "Photo对象", description = "")
public class Photo extends Model<Photo> {

    private static final long serialVersionUID = 1L;

    private Long pId;

    private Long rId;

    private Long hId;

    private String photoUrl;

    public Photo(Long rId, Long hId, String photoUrl) {
        this.rId = rId;
        this.hId = hId;
        this.photoUrl = photoUrl;
    }
    public Photo(){

    }


    @Override
    protected Serializable pkVal() {
        return this.pId;
    }

}
