package com.ducetech.app.service.impl;

import com.ducetech.app.dao.DepartmentDAO;
import com.ducetech.app.dao.UserDAO;
import com.ducetech.app.model.Department;
import com.ducetech.app.model.Role;
import com.ducetech.app.model.User;
import com.ducetech.app.service.DepartmentService;
import com.ducetech.app.service.RoleService;
import com.ducetech.app.service.UserService;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.ducetech.framework.util.Digests;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService{


    @Autowired
    private DepartmentDAO departmentDAO;
	
    @Override
    public Department selectDepartmentByNodeName(String nodeName) {
        return departmentDAO.selectDepartmentByNodeName(nodeName);
    }


    @Override
    public Department selectDepartmentByNodeCode(String nodeCode) {
        return departmentDAO.selectDepartmentByNodeCode(nodeCode);
    }

    @Override
    public List<Department> getAllDepartment() {
        return this.getDepartmentByPager(new BaseQuery<Department>(new Department())).getResults();
    }


    @Override
    public List<Department> getDepartmentByQuery(Department department) {
        return departmentDAO.selectDepartment(department);
    }

    @Override
    public PagerRS<Department> getDepartmentByPager(BaseQuery<Department> query) {
        if( query != null && query.getPage() > 0 ){			//如果传入offset大于0,则启用分页查询，否则不启用
            PageHelper.startPage(query.getPage(), query.getRows(), true);
        }
        List<Department> deptList = departmentDAO.selectDepartment(query.getT());
        @SuppressWarnings({ "unchecked", "rawtypes" })
        PageInfo page = new PageInfo(deptList);
        PagerRS<Department> pagerRS = new PagerRS<Department>(deptList, page.getTotal(), page.getPages());
        return pagerRS;
    }

    @Override
    public List<String> selectDepartmentByParentCode(String parentCode) {
        return departmentDAO.selectDepartmentByParentCode(parentCode);
    }

    @Override
    public List<Department> selectDepartment(Department dept) {
        return departmentDAO.selectDepartment(dept);
    }

    @Override
    public void insertDepartment(Department dept) {
        departmentDAO.insertDepartment(dept);
    }

    @Override
    public void updateDepartment(Department dept) {
        departmentDAO.updateDepartment(dept);
    }

    @Override
    public void deleteDepartmentById(String deptId, String isDeleted) {
        Department dept = departmentDAO.selectDepartmentByNodeCode(deptId);
        dept.setIfUse(1);
        departmentDAO.updateDepartment(dept);
    }
}
