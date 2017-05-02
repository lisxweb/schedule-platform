package com.ducetech.app.service.impl;

import com.ducetech.app.dao.DepartmentDAO;
import com.ducetech.app.model.Department;
import com.ducetech.app.service.DepartmentService;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public List<Department> getAllDepartments() {
        return this.getDepartmentByPager(new BaseQuery<Department>(new Department())).getResults();
    }

    @Override
    public List<Department> getDepartmentByQuery(Department dept) {
        return departmentDAO.selectDepartment(dept);
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
    public List<Department> selectDepartment(Department dept) {
        return departmentDAO.selectDepartment(dept);
    }

    @Override
    public void insertDepartment(Department dept) {
        departmentDAO.insertDepartment(dept);
    }

    @Override
    public String selectDepartmentByParentCode(String parentCode){
        List<Department> nodes = querySubNodesByCode(parentCode);
        String newCode="001";
        if(!nodes.isEmpty()){
            Department node = nodes.get(nodes.size()-1);
            String nodeCode = node.getNodeCode();
            String subCode = nodeCode.substring(nodeCode.length()-3, nodeCode.length());
            int current = Integer.parseInt(subCode);
            current = current+1;
            newCode = leftJoin(current, 3, "0");
        }

        return parentCode+newCode;
    }
    public static String leftJoin(Object obj,int scale,String val){
        String str = String.valueOf(obj);
        int len = str.length();
        if(len<scale){
            int diff = scale-len;
            String prefix="";
            for(int i=0;i<diff;i++){
                prefix+=val;
            }
            str = prefix+str;
        }
        return str;
    }
    public List<Department> querySubNodesByCode(String parentCode){
        return departmentDAO.selectByParentCode(parentCode);
    }
    @Override
    public void updateDepartment(Department dept) {
        departmentDAO.updateDepartment(dept);
    }

    @Override
    public void deleteDepartment(String nodeCode) {
        Department dept = departmentDAO.selectDepartmentByNodeCode(nodeCode);
        dept.setIfUse(1);
        departmentDAO.updateDepartment(dept);
    }

    @Override
    public Department selectDepartmentByNodeCode(String nodeCode) {
        return departmentDAO.selectDepartmentByNodeCode(nodeCode);
    }
}
