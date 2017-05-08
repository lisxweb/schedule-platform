package com.ducetech.app.dao;

import com.ducetech.app.model.Grouping;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GroupingDAO {

	/** 
	* @Title: selectGrouping  
	* @param dept
	* @return List<Grouping>
	* @Description: Grouping通用查询
	*/
	List<Grouping> selectGrouping(Grouping dept);
	
	/** 
	* @Title: addGrouping  
	* @param dept
	* @Description: 保存新增的人员信息
	*/
	void insertGrouping(Grouping dept);
	
	/** 
	* @Title: updateGrouping  
	* @param dept
	* @Description: 更新人员信息
	*/
	void updateGrouping(Grouping dept);

	/** 
	* @Title: deleteGrouping
	* @param groupCode
	* @Description: 启用禁用人员,默认0位启用
	*/
	void deleteGrouping(@Param("groupCode") String groupCode,@Param("ifUse")int ifUse);

    /**
     * @Title: selectGroupingByGroupName
     * @param groupCode
     * @return Grouping
     * @Description: 按登部门名称查询
     */
    Grouping selectGroupingByGroupCode(@Param("groupCode") String groupCode);

    List<Grouping> selectByParentCode(@Param("groupCode") String parentCode, @Param("groupCodeLength") int groupCodeLength);

}