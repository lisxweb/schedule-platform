package com.ducetech.app.dao;

import com.ducetech.app.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DepartmentDAO {

	/** 
	* @Title: selectDepartment  
	* @param dept
	* @return List<Department>
	* @Description: Department通用查询
	*/
	List<Department> selectDepartment(Department dept);
	
	/** 
	* @Title: addDepartment  
	* @param dept
	* @Description: 保存新增的人员信息
	*/
	void insertDepartment(Department dept);
	
	/** 
	* @Title: updateDepartment  
	* @param dept
	* @Description: 更新人员信息
	*/
	void updateDepartment(Department dept);

	/** 
	* @Title: deleteDepartment
	* @param nodeCode
	* @Description: 启用禁用人员,默认0位启用
	*/
	void deleteDepartment(@Param("nodeCode") String nodeCode);

    /**
     * @Title: selectDepartmentByNodeName
     * @param nodeCode
     * @return Department
     * @Description: 按登部门名称查询
     */
    Department selectDepartmentByNodeCode(@Param("nodeCode") String nodeCode);

    List<Department> selectByParentCode(@Param("nodeCode") String parentCode,@Param("nodeCodeLength") int nodeCodeLength);

}