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
public class ReportController extends BaseController {

    @RequestMapping(value = "/hourReport", method = RequestMethod.GET)
	public String hourReport(Model model, HttpServletRequest request) {
        return "/report/hourReport";
	}
    @RequestMapping(value = "/temporaryReport", method = RequestMethod.GET)
    public String temporaryReport(Model model, HttpServletRequest request) {
        return "/report/temporaryReport";
    }
}
