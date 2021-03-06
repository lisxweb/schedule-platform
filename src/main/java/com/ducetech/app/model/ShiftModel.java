package com.ducetech.app.model;

import com.ducetech.framework.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 班次字典
 * Created by lisx on 2017/4/27.
 */
@Data
public class ShiftModel extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String ShiftModelId;
    // 字典code
    private String nodeCode;

    // 字典名称
    private String nodeName;

    // 字典排序
    private int nodeOrder;

    // 是否使用
    private int ifUse;

    // 创建人
    private long creatorId;

    // 更新人
    private long updatorId;

    // 创建时间
    private Date createdAt;

    // 更新时间
    private Date updatedAt;
}
