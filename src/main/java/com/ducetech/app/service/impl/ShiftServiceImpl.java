package com.ducetech.app.service.impl;

import com.ducetech.app.dao.ShiftDAO;
import com.ducetech.app.model.Shift;
import com.ducetech.app.service.ShiftService;
import com.ducetech.framework.model.BaseQuery;
import com.ducetech.framework.model.PagerRS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService{
    @Autowired
    private ShiftDAO shiftDAO;

    @Override
    public List<Shift> selectShift(Shift dept) {
        return shiftDAO.selectShift(dept);
    }

    @Override
    public List<Shift> getShiftByQuery(Shift dept) {
        return null;
    }

    @Override
    public PagerRS<Shift> getShiftByPager(BaseQuery<Shift> query) {
        return null;
    }

    @Override
    public String selectShiftByParentCode(String parentCode) {
        return null;
    }

    @Override
    public void insertShift(Shift dept) {

    }

    @Override
    public void updateShift(Shift dept) {

    }

    @Override
    public void deleteShift(String shiftCode) {

    }

    @Override
    public Shift selectShiftByGroupCode(String shiftCode) {
        return null;
    }

    @Override
    public List<Shift> selectByParentCode(String parentCode, int shiftCodeLength) {
        return null;
    }
}
