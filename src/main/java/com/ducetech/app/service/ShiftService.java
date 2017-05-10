package com.ducetech.app.service;

import com.ducetech.app.model.Shift;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;

import java.util.List;

public interface ShiftService {

    /**
     * 查询
     * @param dept
     * @return
     */
    List<Shift> selectShift(Shift dept);

    /**
     * @Title: getShiftByQuery
     * @return List<Shift>
     * @Description: 按条件筛选人员,不带分页
     */
    List<Shift> getShiftByQuery(Shift dept);

    /**
     * @Title: getShiftByPager
     * @return PagerRS<Shift>
     * @Description: 按条件筛选人员,带分页
     */
    PagerRS<Shift> getShiftByPager(BaseQuery<Shift> query);

    /**
     * 获取新节点
     * @param parentCode
     * @return
     */
    String selectShiftByParentCode(String parentCode);

    /**
     * @Title: addShift
     * @param dept
     * @Description: 保存新增的人员信息
     */
    void insertShift(Shift dept);

    /**
     * @Title: updateShift
     * @param dept
     * @Description: 更新人员信息
     */
    void updateShift(Shift dept);

    /**
     * @Title: deleteShiftById
     * @param shiftCode
     * @Description: 启用禁用人员,默认0位启用
     */
    void deleteShift(String shiftCode);

    Shift selectShiftByGroupCode(String shiftCode);

    /**
     * 获取对应等级
     * @param parentCode
     * @param shiftCodeLength
     * @return
     */
    List<Shift> selectByParentCode(String parentCode, int shiftCodeLength);
}
