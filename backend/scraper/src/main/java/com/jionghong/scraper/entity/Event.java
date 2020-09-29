package com.jionghong.scraper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.experimental.Accessors;

/**
 * <p>
 * value object  and DAO
 * </p>
 *
 * @author jionghong
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Event对象", description="")
public class Event implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String eventDate;

    private String eventLocation;

    private String eventName;

    private String websiteName;


}
