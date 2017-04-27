package com.ducetech.app.service;

import com.ducetech.app.model.Role;
import com.ducetech.app.model.Department;
import com.ducetech.app.model.User;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentService {

    /**
     * @Title: selectDepartmentByNodeName
     * @param nodeName
     * @return Department
     * @Description: 按登部门名称查询
     */
    Department selectDepartmentByNodeName(String nodeName);

    Department selectDepartmentByNodeCode(String nodeCode);
    /**
     * @Title: getAllUsers
     * @return List<Department>
     * @Description: 获取所有人员
     */
    List<Department> getAllDepartment();

    /**
     * @Title: getUserByQuery
     * @return List<Department>
     * @Description: 按条件筛选人员,不带分页
     */
    List<Department> getDepartmentByQuery(Department department);

    /**
     * @Title: getDepartmentByPager
     * @return PagerRS<Department>
     * @Description: 按条件筛选人员,带分页
     */
    PagerRS<Department> getDepartmentByPager(BaseQuery<Department> query);


    /**
     * @Title: selectPermissionByDepartmentId
     * @param parentCode
     * @return List<String>
     * @Description: 获取某用户的全部菜单权限
     */
    List<String> selectDepartmentByParentCode(String parentCode);

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
    void deleteDepartmentById(String deptId,String isDeleted);

}
