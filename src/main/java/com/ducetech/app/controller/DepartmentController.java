package com.ducetech.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.ducetech.app.model.Department;
import com.ducetech.app.model.User;
import com.ducetech.app.service.DepartmentService;
import com.ducetech.framework.controller.BaseController;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.ducetech.framework.util.DateUtil;
import com.ducetech.framework.web.view.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
public class DepartmentController extends BaseController {
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DepartmentService departmentService;

	/**
	 * @Title: person
	 * @param model
	 * @return String
	 * @Description: 跳转部门首页
	 */
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public String departments(Model model) {
		return "/department/index";
	}

	/**
	 * @Title: personData
	 * @return void
	 * @throws Exception
	 * @Description: 部门首页数据
	 */
	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	@ResponseBody
	public PagerRS<Department> departmentData(HttpServletRequest request) throws Exception {
		BaseQuery<Department> query = Department.getSearchCondition(Department.class, request);
		PagerRS<Department> rs = departmentService.getDepartmentByPager(query);
		return rs;
	}

    /**
     * @Title: addDepartment
     * @return void
     * @Description: 新增站区
     */
    @RequestMapping(value = "/dept/addStationAreaForm", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject create(Department dept, String parentCode, HttpServletRequest request) throws Exception {
        User userInfo = getLoginUser(request);
        dept.setCreatedAt(DateUtil.formatDate(new Date(), DateUtil.DEFAULT_TIME_FORMAT));
        dept.setCreatorId(userInfo.getUserId());
        dept.setIfUse(0);
        dept.setNodeOrder(0);
        if(parentCode==null) {
            dept.setNodeCode(departmentService.selectDepartmentByParentCode("000"));
        }else{
            dept.setNodeCode(departmentService.selectDepartmentByParentCode(parentCode));
        }
        departmentService.insertDepartment(dept);
        JSONObject obj = new JSONObject();
        obj.put("msg","新增站区成功");
        return obj;
    }

    /**
     * 编辑部门
     */

    /**
     * @param nodeCode
     * @Description: 跳转人员编辑页面
     */
    @RequestMapping(value = "/department/{nodeCode}", method = RequestMethod.GET)
    @ResponseBody
    public Department edit(@PathVariable(value="nodeCode")String nodeCode) throws IOException {
        Department dept = new Department();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(nodeCode)){
            dept = departmentService.selectDepartmentByNodeCode(nodeCode);
        }
        return dept;
    }

    /**
     * @param dept
     * @Description: 更新人员信息
     */
    @RequestMapping(value = "/departments", method = RequestMethod.PUT)
    @ResponseBody
    public OperationResult update(Department dept) throws IOException {
        departmentService.updateDepartment(dept);
        return OperationResult.buildFailureResult("成功", 1);
    }
}