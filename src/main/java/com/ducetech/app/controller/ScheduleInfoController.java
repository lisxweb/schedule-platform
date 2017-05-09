package com.ducetech.app.controller;

import com.ducetech.framework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * 
* @ClassName: MainPageController 
* @Description: 系统主页Controller 
* @date 2016年9月18日 下午5:38:26
*
 */
@Controller
public class ScheduleInfoController extends BaseController {

    @RequestMapping(value = "/scheduleForm", method = RequestMethod.GET)
	public String scheduleForm(Model model, HttpServletRequest request) {
        return "/scheduleInfo/scheduleForm";
	}
    @RequestMapping(value = "/automaticScheduling", method = RequestMethod.GET)
    public String automaticScheduling(Model model, HttpServletRequest request) {
        return "/scheduleInfo/automaticScheduling";
    }
    @RequestMapping(value = "/shiftApproval", method = RequestMethod.GET)
    public String shiftApproval(Model model, HttpServletRequest request) {
        return "/scheduleInfo/shiftApproval";
    }
}
