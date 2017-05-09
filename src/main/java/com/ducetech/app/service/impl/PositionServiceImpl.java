package com.ducetech.app.service.impl;

import com.ducetech.app.dao.PositionDAO;
import com.ducetech.app.model.Position;
import com.ducetech.app.service.PositionService;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService{

    @Autowired
    private PositionDAO positionDAO;

    @Override
    public List<Position> getAllPositions() {
        return this.getPositionByPager(new BaseQuery<Position>(new Position())).getResults();
    }

    @Override
    public List<Position> getPositionByQuery(Position dept) {
        return positionDAO.selectPosition(dept);
    }

    @Override
    public PagerRS<Position> getPositionByPager(BaseQuery<Position> query) {
        if( query != null && query.getPage() > 0 ){			//如果传入offset大于0,则启用分页查询，否则不启用
            PageHelper.startPage(query.getPage(), query.getRows(), true);
        }
        List<Position> deptList = positionDAO.selectPosition(query.getT());
        @SuppressWarnings({ "unchecked", "rawtypes" })
        PageInfo page = new PageInfo(deptList);
        PagerRS<Position> pagerRS = new PagerRS<Position>(deptList, page.getTotal(), page.getPages());
        return pagerRS;
    }

    @Override
    public void insertPosition(Position dept) {
        positionDAO.insertPosition(dept);
    }
    /**
     * 获取新的节点编号
     * @param parentCode
     * @return
     */
    @Override
    public String selectPositionByParentCode(String parentCode){
        List<Position> groups = querySubPositionsByCode(parentCode);
        System.out.println(groups.size()+"|||");
        String newCode="001";
        if(!groups.isEmpty()){
            Position group = groups.get(groups.size()-1);
            String groupCode = group.getPositionCode();
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
    public List<Position> querySubPositionsByCode(String parentCode){
        System.out.println(parentCode+"||"+(parentCode.length()+3));
        return positionDAO.selectByParentCode(parentCode+"%",parentCode.length()+3);
    }
    @Override
    public void updatePosition(Position dept) {
        positionDAO.updatePosition(dept);
    }

    @Override
    public void deletePosition(String groupCode) {
        positionDAO.deletePosition(groupCode+"%",1);
    }

    @Override
    public Position selectPositionByPositionCode(String groupCode) {
        return positionDAO.selectPositionByPositionCode(groupCode);
    }

    @Override
    public List<Position> selectByParentCode(String parentCode, int groupCodeLength) {
        List<Position> deptList = positionDAO.selectByParentCode(parentCode+"%",groupCodeLength);
        List<Position> list = new ArrayList<Position>();
        for(Position dep:deptList){
            list.add(dep);
        }
        return list;
    }
}
