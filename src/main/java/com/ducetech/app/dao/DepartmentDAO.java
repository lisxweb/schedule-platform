package com.ducetech.app.dao;

import com.ducetech.app.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DepartmentDAO {
	
	
	/** 
	* @Title: selectDepartmentByNodeName
	* @param nodeName
	* @return Department
	* @Description: 按登部门名称查询
	*/
	Department selectDepartmentByNodeName(@Param("nodeName") String nodeName);

    Department selectDepartmentByNodeCode(@Param("nodeCode") String nodeCode);
	
	/** 
	* @Title: selectPermissionByDepartmentId  
	* @param parentCode
	* @return List<String>
	* @Description: 获取某用户的全部菜单权限
	*/
	List<String> selectDepartmentByParentCode(@Param("parentCode") String parentCode);

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
	* @Title: deleteDepartmentById 
	* @param deptId , isDeleted
	* @Description: 启用禁用人员,默认0位启用
	*/
	void deleteDepartmentById(@Param("deptId") String deptId, @Param("isDeleted") String isDeleted);

}