package com.ducetech.app.model;

import com.ducetech.framework.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 排班日志
 * Created by lisx on 2017/4/27.
 */
public class ScheduleLog extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String scheduleLogId;
    //排版详情
    private String scheduleId;
    //排班人员
    private String userId;
    //操作记录
    private String remark;
    //创建人
    private String creator;
    //创建时间
    private Date createdAt;
    //统计 info
}
