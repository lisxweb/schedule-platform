package com.ducetech.app.model;

import com.ducetech.framework.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 排版详情
 * Created by lisx on 2017/4/27.
 */
public class ScheduleInfo extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scheduleInfoId;
    //排版类型
    private String scheduleType;
    //当班总时间
    private Integer totalAt;
    //排班人员
    private String userId;
    //排班日期
    private Date scheduleDate;
    //星期几
    private String scheduleWeek;
    //所属站点
    private String station;
    //所属站区
    private String stationArea;
    //备注
    private String scheduleDesc;
    //是否请假
    private int ifLeave;
    //请假类型
    private String leaveType;
    //岗位类型
    private String jobType;
    //创建人
    private String creator;
    //创建时间
    private Date createdAt;
}
