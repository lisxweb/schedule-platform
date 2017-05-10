package com.ducetech.app.dao;

import com.ducetech.app.model.Shift;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ShiftDAO {

	/**
	* @Title: selectShift
	* @param dept
	* @return List<Shift>
	* @Description: Shift通用查询
	*/
	List<Shift> selectShift(Shift dept);

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
	* @Title: deleteShift
	* @param groupCode
	* @Description: 启用禁用人员,默认0位启用
	*/
	void deleteShift(@Param("groupCode") String groupCode, @Param("ifUse") int ifUse);

    /**
     * @Title: selectShiftByGroupName
     * @param groupCode
     * @return Shift
     * @Description: 按登部门名称查询
     */
    Shift selectShiftByGroupCode(@Param("groupCode") String groupCode);

    List<Shift> selectByParentCode(@Param("groupCode") String parentCode, @Param("groupCodeLength") int groupCodeLength);

}