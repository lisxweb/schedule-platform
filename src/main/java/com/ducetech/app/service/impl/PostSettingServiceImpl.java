package com.ducetech.app.service.impl;

import com.ducetech.app.dao.PostSettingDAO;
import com.ducetech.app.model.PostSetting;
import com.ducetech.app.service.PostSettingService;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostSettingServiceImpl implements PostSettingService{

    @Autowired
    private PostSettingDAO positionDAO;

    @Override
    public List<PostSetting> getAllPostSettings() {
        return this.getPostSettingByPager(new BaseQuery<PostSetting>(new PostSetting())).getResults();
    }

    @Override
    public List<PostSetting> getPostSettingByQuery(PostSetting dept) {
        return positionDAO.selectPostSetting(dept);
    }

    @Override
    public PagerRS<PostSetting> getPostSettingByPager(BaseQuery<PostSetting> query) {
        if( query != null && query.getPage() > 0 ){			//如果传入offset大于0,则启用分页查询，否则不启用
            PageHelper.startPage(query.getPage(), query.getRows(), true);
        }
        List<PostSetting> deptList = positionDAO.selectPostSetting(query.getT());
        @SuppressWarnings({ "unchecked", "rawtypes" })
        PageInfo page = new PageInfo(deptList);
        PagerRS<PostSetting> pagerRS = new PagerRS<PostSetting>(deptList, page.getTotal(), page.getPages());
        return pagerRS;
    }

    @Override
    public void insertPostSetting(PostSetting dept) {
        positionDAO.insertPostSetting(dept);
    }
    /**
     * 获取新的节点编号
     * @param parentCode
     * @return
     */
    @Override
    public String selectPostSettingByParentCode(String parentCode){
        List<PostSetting> groups = querySubPostSettingsByCode(parentCode);
        System.out.println(groups.size()+"|||");
        String newCode="001";
        if(!groups.isEmpty()){
            PostSetting group = groups.get(groups.size()-1);
            String groupCode = group.getPostCode();
            String subCode = groupCode.substring(groupCode.length()-3, groupCode.length());
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
    public List<PostSetting> querySubPostSettingsByCode(String parentCode){
        System.out.println(parentCode+"||"+(parentCode.length()+3));
        return positionDAO.selectByParentCode(parentCode+"%",parentCode.length()+3);
    }
    @Override
    public void updatePostSetting(PostSetting dept) {
        positionDAO.updatePostSetting(dept);
    }

    @Override
    public void deletePostSetting(String groupCode) {
        positionDAO.deletePostSetting(groupCode+"%",1);
    }

    @Override
    public PostSetting selectPostSettingByPostCode(String groupCode) {
        return positionDAO.selectPostSettingByPostCode(groupCode);
    }

    @Override
    public List<PostSetting> selectByParentCode(String parentCode, int groupCodeLength) {
        List<PostSetting> deptList = positionDAO.selectByParentCode(parentCode+"%",groupCodeLength);
        List<PostSetting> list = new ArrayList<PostSetting>();
        for(PostSetting dep:deptList){
            list.add(dep);
        }
        return list;
    }
}
