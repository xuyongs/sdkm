package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.AppBaseSetMapper;
import com.java.service.AppBaseSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AppBaseSetServiceImpl implements AppBaseSetService {
    @Autowired
    private AppBaseSetMapper appBaseSetMapper;

    @Override
    public List<Map<String, Object>> findAdvices(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        return appBaseSetMapper.selectAdvice();
    }

    @Override
    public boolean modifyMany(String idStr) {
        if (idStr == null || idStr == "")
            return false;
        idStr = idStr.substring(0, idStr.lastIndexOf(","));
        return appBaseSetMapper.updateMany(idStr) >= 1;
    }

    @Override
    public boolean modifyOne(Long id) {
        try {
            appBaseSetMapper.updateOne(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Map<String, Object> findAbout() {
        return appBaseSetMapper.selectAbout();
    }

    @Override
    public boolean updateAbout(String content, Long id) {
        return appBaseSetMapper.updateAbout(content, id) >= 1;
    }


}





















