package com.ducetech.app.controller;


import com.ducetech.app.model.Role;
import com.ducetech.app.model.User;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
public class UserController extends BaseController {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) {
		if (request.getSession().getAttribute("DT_LOGIN_NAME")!=null) {
			return "redirect:/";
		}
		return "login";
	}
    @RequestMapping(value = "/user/toImport", method = RequestMethod.GET)
    public String toImport(HttpServletRequest request){
        return "redirect:/user/import";
    }


    @RequestMapping("/import")
    public ModelAndView importFile(@RequestParam(value="uploadFile")MultipartFile mFile, HttpServletRequest request, HttpServletResponse response){
        String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
        List<User> secUserList = userService.importFile(mFile, rootPath);

        ModelAndView mv = new ModelAndView();
        mv.addObject("type", "import");
        mv.addObject("secUserList", secUserList);
        mv.setViewName("/success");
        return mv;
    }
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, RedirectAttributesModelMap modelMap) {
		String userCode = request.getParameter("userCode");
		String password = request.getParameter("password");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userCode,password);
		if (StringUtils.isNotEmpty(userCode) && StringUtils.isNotEmpty(password)) {
			try{
				subject.login(token);	
				User user = (User)subject.getPrincipal();
				log.info("{}:{} login.", userCode, user.getUserName());
				subject.getSession().setAttribute("DT_LOGIN_USER", user);
				CookieUtil.setCookie(response, "", user.getUserId());
			}catch(Exception ex){
				//ex.printStackTrace();
				modelMap.addFlashAttribute("message", "账号或密码错误");
				return "redirect:/login";
			}
		}
		return "redirect:/";
	} 

	@RequestMapping(value = "/users/info", method = RequestMethod.GET)
	public String findUser(HttpServletRequest request, Model model) {
		User user = getLoginUser(request);
		model.addAttribute("user", user);
		return "user/user-info";
	}
	
	@RequestMapping(value = "/users/info", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("form") User user, HttpServletRequest request, Model model) {
		String name = user.getUserName();
		
		User userInfo = getLoginUser(request);
		userInfo.setUserName(name);
		
//		userService.editUserInfo(userInfo);
		model.addAttribute("msg", "success");
		model.addAttribute("user", userInfo);
		return "user/user-info";
	}

	@RequestMapping(value = "/users/password", method = RequestMethod.GET)
	public String password(Model model) {
		return "/user/password";
	}

	/**
	 * @Title: person
	 * @param model
	 * @return String
	 * @Description: 跳转人员首页
	 */
	@RequestMapping(value = "/users/person", method = RequestMethod.GET)
	public String person(Model model) {
		Role role = new Role();
		role.setIsDeleted("0");
		List<Role> roles = roleService.getRoleByQuery(role);
		model.addAttribute("roles", roles);
		return "/user/users";
	}

	/**
	 * @Title: personData
	 * @return void
	 * @throws Exception
	 * @Description: 人员首页数据
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public PagerRS<User> personData(HttpServletRequest request) throws Exception {
		BaseQuery<User> query = User.getSearchCondition(User.class, request);

		query.getT().setUserCode(query.getT().getUserCode().trim());
		query.getT().setUserName(query.getT().getUserName().trim());
		PagerRS<User> rs = userService.getUserByPager(query);
		System.out.println(rs.getCount()+"|||||");
		return rs;
	}

	/**
	 * @Title: addUser
	 * @return void
	 * @throws java.io.IOException
	 * @Description: 新增人员
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public OperationResult create(User user, HttpServletRequest request) throws IOException {
	    System.out.println(user.getUserCode()+"|||||||||");
		User userInfo = getLoginUser(request);
		User ur = new User();
		ur.setUserCode(user.getUserCode());
		User uCode = userService.getUserByUserCode(user.getUserCode());
		if(uCode!=null) {
			return OperationResult.buildFailureResult("工号已存在", 0);
		} else {
			user.setCreatorId(userInfo.getUserId());
			user.setIsAdmin(user.getRoleIds()!=""?0:1);
			user.setCreatedAt(DateUtil.formatDate(new Date(), DateUtil.DEFAULT_TIME_FORMAT));
			userService.addUser(user);
			return OperationResult.buildSuccessResult("成功", 1);
		}
	}

	/**
	 * @param userId
	 * @Description: 跳转人员编辑页面
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User edit(@PathVariable(value="id")String userId) throws IOException {
		User user = new User();
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(userId)){
			user = userService.getUserByUserId(userId);
		}
		return user;
	}

	/**
	 * @param user
	 * @Description: 更新人员信息
	 */
	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	@ResponseBody
	public OperationResult update(User user) throws IOException {
		List<User> uList = userService.getAllUsers();
		for (int i = 0; i < uList.size(); i++) {
			if(uList.get(i).getUserId().equals(user.getUserId())){
				uList.remove(i);
			}
		}
		for (User us : uList) {
			if (us.getUserCode().equals(user.getUserCode())) {
				return OperationResult.buildFailureResult("工号已存在", 0);
			}
		}
		userService.updateUser(user);
		return OperationResult.buildFailureResult("成功", 1);
	}


	/**
	 * @Description: 人员禁用启用
	 */
	@RequestMapping(value = "/users/{id}/updateStatus", method = RequestMethod.PUT)
	@ResponseBody
	public OperationResult updateStatus(@PathVariable(value="id") String userId, int isDeleted) throws IOException {
		User user = new User();
		user.setUserId(userId);
		user.setIsDeleted(isDeleted);
		userService.updateUserStatus(user);
		return OperationResult.buildFailureResult("成功", 1);
	}

	/**
	 * @Title: resetPass
	 * @Description: 重置密码为123456
	 */
	@RequestMapping(value = "/users/{id}/resetPass", method = RequestMethod.GET)
	@ResponseBody
	public OperationResult resetPass(@PathVariable(value="id") String userId) throws IOException {
		User user = userService.getUserByUserId(userId);
		System.out.println(user.getPassword()+"||||||");
		user.setPassword(Digests.md5Hash("123456", user.getSecretKey()));
		userService.resetPass(user);
		return OperationResult.buildFailureResult("重置成功，密码为：123456", 1);
	}

//	public static void main(String [] args){
//        System.out.println(Digests.md5Hash("123456", "cb7e52304f0d11e6965c00ff2c2e2b3f"));
//    }
}
