package com.ducetech.app.model;

import com.ducetech.framework.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 班次设置
 * Created by lisx on 2017/4/27.
 */
@Data
public class Shift extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String shiftId;
    //班次名称
    private String shiftName;
    //班次人数
    private int shiftNum;
    //班次颜色
    private String shiftColor;
    //开始时间
    private String startAt;
    //结束时间
    private String endAt;
    //总时间
    private Integer totalAt;
    //间隔时间
    private Integer intervalAt;
    //关联班次
    private String relevance;
    //所属站点
    private String station;
    //所属站区
    private String stationArea;
    //是否删除
    private int ifUse;
    //创建人
    private String creatorId;
    //更新人
    private String updatorId;
    //创建时间
    private String createdAt;
    //更新时间
    private String updatedAt;
}
