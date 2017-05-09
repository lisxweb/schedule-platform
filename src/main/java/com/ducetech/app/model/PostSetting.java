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
public class PostSetting extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private String postId;
    // 字典code
    private String postCode;

    // 岗位名称
    private String postName;

    // 月工时上限(时)
    private String postMonth;

    // 年工时上限(时)
    private String postYear;

    // 每周最少休班(天)
    private String postWeekly;

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
