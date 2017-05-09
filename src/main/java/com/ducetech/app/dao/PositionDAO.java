package com.ducetech.app.dao;

import com.ducetech.app.model.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PositionDAO {

	/**
	* @Title: selectPosition
	* @param dept
	* @return List<Position>
	* @Description: Position通用查询
	*/
	List<Position> selectPosition(Position dept);

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
	* @Title: deletePosition
	* @param positionCode
	* @Description: 启用禁用人员,默认0位启用
	*/
	void deletePosition(@Param("positionCode") String positionCode, @Param("ifUse") int ifUse);

    /**
     * @Title: selectPositionByGroupName
     * @param positionCode
     * @return Position
     * @Description: 按登部门名称查询
     */
    Position selectPositionByPositionCode(@Param("positionCode") String positionCode);

    List<Position> selectByParentCode(@Param("positionCode") String parentCode, @Param("positionCodeLength") int positionCodeLength);

}