package com.ducetech.app.controller;


import com.ducetech.app.model.Department;
import com.ducetech.app.model.Role;
import com.ducetech.app.model.User;
import com.ducetech.app.service.DepartmentService;
import com.ducetech.app.service.RoleService;
import com.ducetech.app.service.UserService;
import com.ducetech.framework.controller.BaseController;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.ducetech.framework.util.CookieUtil;
import com.ducetech.framework.util.DateUtil;
import com.ducetech.framework.util.Digests;
import com.ducetech.framework.web.view.OperationResult;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @Title: addUser
     * @return void
     * @Description: 新增部门
     */
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    @ResponseBody
    public OperationResult create(Department dept) throws Exception {
        dept.setCreatedAt(DateUtil.formatDate(new Date(), DateUtil.DEFAULT_TIME_FORMAT));
        departmentService.insertDepartment(dept);
        return OperationResult.buildSuccessResult("成功", 1);
    }


}