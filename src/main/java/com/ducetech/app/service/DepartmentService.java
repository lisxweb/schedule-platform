package com.ducetech.app.service;

import com.ducetech.app.model.Department;
import com.ducetech.app.model.Department;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;

import java.util.List;

public interface DepartmentService {

    /**
     * @Title: getAllDepartments
     * @return List<Department>
     * @Description: 获取所有人员
     */
    List<Department> getAllDepartments();

    /**
     * @Title: getDepartmentByQuery
     * @return List<Department>
     * @Description: 按条件筛选人员,不带分页
     */
    List<Department> getDepartmentByQuery(Department dept);

    /**
     * @Title: getDepartmentByPager
     * @return PagerRS<Department>
     * @Description: 按条件筛选人员,带分页
     */
    PagerRS<Department> getDepartmentByPager(BaseQuery<Department> query);


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
     * @param nodeCode
     * @Description: 启用禁用人员,默认0位启用
     */
    void deleteDepartment(String nodeCode);

}
