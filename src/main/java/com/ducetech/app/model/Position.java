package com.ducetech.app.model;

import com.ducetech.framework.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 岗位字典
 * Created by lisx on 2017/4/27.
 */
@Data
public class Position extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private String positionId;
    // 字典code
    private String positionCode;

    // 岗位名称
    private String positionName;

    // 月工时上限(时)
    private String month;

    // 年工时上限(时)
    private String year;

    // 每周最少休班(天)
    private String weekly;

    // 是否使用
    private int ifUse;

    // 创建人
    private String creatorId;

    // 更新人
    private String updatorId;

    // 创建时间
    private String createdAt;

    // 更新时间
    private String updatedAt;
}
