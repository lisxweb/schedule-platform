package com.ducetech.app.service;

import com.ducetech.app.model.Position;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;

import java.util.List;

public interface PositionService {

    /**
     * @Title: getAllPositions
     * @return List<Position>
     * @Description: 获取所有人员
     */
    List<Position> getAllPositions();

    /**
     * @Title: getPositionByQuery
     * @return List<Position>
     * @Description: 按条件筛选人员,不带分页
     */
    List<Position> getPositionByQuery(Position dept);

    /**
     * @Title: getPositionByPager
     * @return PagerRS<Position>
     * @Description: 按条件筛选人员,带分页
     */
    PagerRS<Position> getPositionByPager(BaseQuery<Position> query);

    /**
     * 获取新节点
     * @param parentCode
     * @return
     */
    String selectPositionByParentCode(String parentCode);

    /**
     * @Title: addPosition
     * @param dept
     * @Description: 保存新增的人员信息
     */
    void insertPosition(Position dept);

    /**
     * @Title: updatePosition
     * @param dept
     * @Description: 更新人员信息
     */
    void updatePosition(Position dept);

    /**
     * @Title: deletePositionById
     * @param positionCode
     * @Description: 启用禁用人员,默认0位启用
     */
    void deletePosition(String positionCode);

    Position selectPositionByPositionCode(String positionCode);

    /**
     * 获取对应等级
     * @param parentCode
     * @param positionCodeLength
     * @return
     */
    List<Position> selectByParentCode(String parentCode, int positionCodeLength);
}
