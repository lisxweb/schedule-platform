package com.ducetech.app.controller;

import com.ducetech.app.model.PostSetting;
import com.ducetech.app.model.Shift;
import com.ducetech.app.model.User;
import com.ducetech.app.service.PostSettingService;
import com.ducetech.app.service.ShiftService;
import com.ducetech.app.service.UserService;
import com.ducetech.framework.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 
* @ClassName: MainPageController 
* @Description: 系统主页Controller 
* @date 2016年9月18日 下午5:38:26
*
 */
@Controller
public class ShiftController extends BaseController {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private PostSettingService postSettingService;

    @RequestMapping(value = "/shift", method = RequestMethod.GET)
	public String shift(ModelMap model, HttpServletRequest request) {
        List<PostSetting> postList = postSettingService.getAllPostSettings();
        System.out.println(postList.size()+"||||||||");
        model.put("postList",postList);
        return "/shift/index";
	}
}
