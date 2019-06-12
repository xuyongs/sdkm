package com.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppBaseSetService {
    List<Map<String, Object>> findAdvices(Integer page, Integer rows);

    boolean modifyMany(@Param("idStr") String idStr);

    boolean modifyOne(@Param("id") Long id);

    Map<String, Object> findAbout();

    boolean updateAbout(@Param("content") String content, @Param("id") Long id);


}











