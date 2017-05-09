package com.ducetech.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.ducetech.app.model.PostSetting;
import com.ducetech.app.model.User;
import com.ducetech.app.service.PostSettingService;
import com.ducetech.framework.controller.BaseController;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.ducetech.framework.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class PostSettingController extends BaseController {
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PostSettingService postSettingService;

	@RequestMapping(value = "/postSetting", method = RequestMethod.GET)
	public String postSettings(ModelMap model) {
        return "/postSetting/index";
	}

	/**
	 * @Title: personData
	 * @return void
	 * @throws Exception
	 * @Description: 部门首页数据
	 */
	@RequestMapping(value = "/postSettings", method = RequestMethod.GET)
	@ResponseBody
	public List<PostSetting> postSettingData(HttpServletRequest request) throws Exception {
		List<PostSetting> stationArea = postSettingService.selectByParentCode("000",6);
		System.out.println(stationArea.size()+"||||||");
		return stationArea;
	}

    /**
     * @Title: addPostSetting
     * @return void
     * @Description: 新增站区
     */
    @RequestMapping(value = "/addPostSettingForm", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addPostSettingForm(PostSetting postSetting, String parentCode, HttpServletRequest request) throws Exception {
        User userInfo = getLoginUser(request);
        postSetting.setCreatedAt(DateUtil.formatDate(new Date(), DateUtil.DEFAULT_TIME_FORMAT));
        postSetting.setCreatorId(userInfo.getUserId());
        postSetting.setIfUse(0);
        if(parentCode==null) {
            postSetting.setPostCode(postSettingService.selectPostSettingByParentCode("000"));
        }else{
            postSetting.setPostCode(postSettingService.selectPostSettingByParentCode(parentCode));
        }
        postSettingService.insertPostSetting(postSetting);
        JSONObject obj = new JSONObject();
        obj.put("msg","新增站区成功");
        return obj;
    }
    /**
     * @Description: 更新站区
     */
    @RequestMapping(value = "/postSetting/editStationAreaForm", method = RequestMethod.PUT)
    @ResponseBody
    public JSONObject editStationAreaForm(PostSetting postSetting, HttpServletRequest request) throws Exception {
        System.out.println(postSetting.getPostName()+"|"+new String(postSetting.getPostName().getBytes("ISO-8859-1"),"utf-8"));
        postSetting.setPostName(new String(postSetting.getPostName().getBytes("ISO-8859-1"),"utf-8"));
        User userInfo = getLoginUser(request);
        postSetting.setUpdatedAt(DateUtil.formatDate(new Date(), DateUtil.DEFAULT_TIME_FORMAT));
        postSetting.setUpdatorId(userInfo.getUserId());
        postSettingService.updatePostSetting(postSetting);
        JSONObject obj = new JSONObject();
        obj.put("msg"," 更新站区成功");
        return obj;
    }

    /**
     * 添加站点
     * @param postSetting
     * @param parentCode
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/postSetting/addStationForm", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addStationForm(PostSetting postSetting, String parentCode, HttpServletRequest request) throws Exception {
        User userInfo = getLoginUser(request);
        postSetting.setCreatedAt(DateUtil.formatDate(new Date(), DateUtil.DEFAULT_TIME_FORMAT));
        postSetting.setCreatorId(userInfo.getUserId());
        postSetting.setIfUse(0);
        if(parentCode==null) {
            postSetting.setPostCode(postSettingService.selectPostSettingByParentCode("000"));
        }else{
            postSetting.setPostCode(postSettingService.selectPostSettingByParentCode(parentCode));
        }
        postSettingService.insertPostSetting(postSetting);
        JSONObject obj = new JSONObject();
        obj.put("msg","新增站点成功");
        return obj;
    }
    /**
     * 编辑部门
     */

    /**
     * @param postSettingCode
     * @Description: 跳转人员编辑页面
     */
    @RequestMapping(value = "/postSetting/{postSettingCode}", method = RequestMethod.GET)
    @ResponseBody
    public PostSetting edit(@PathVariable(value="postSettingCode")String postSettingCode) throws Exception {
        PostSetting postSetting = new PostSetting();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(postSettingCode)){
            postSetting = postSettingService.selectPostSettingByPostCode(postSettingCode);
        }
        return postSetting;
    }

    @RequestMapping(value = "/postSetting/delStationAreaForm", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delStationArea(String postSettingCode) throws Exception {
        System.out.println(postSettingCode+"|||");
        postSettingService.deletePostSetting(postSettingCode);
        JSONObject obj = new JSONObject();
        if(postSettingCode.length()==6){
            obj.put("msg","删除站区成功");
        }else{
            obj.put("msg","删除站点成功");
        }

        return obj;
    }
}