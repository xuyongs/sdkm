package com.java.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface AppBaseSetMapper {
    @Select("SELECT sa.*,au.uName FROM system_advice sa INNER JOIN app_users au ON sa.appUserId=au.id")
    List<Map<String, Object>> selectAdvice();

    @Update("update system_advice set status='1' where id in(${idStr})")
    int updateMany(@Param("idStr") String idStr);

    void updateOne(@Param("id") Long id);

    @Select("select * from app_notice where type='1' order by updateTime desc limit 1")
    Map<String, Object> selectAbout();

    @Update("update app_notice set content=#{content},updateTime=NOW() where id=#{id}")
    int updateAbout(@Param("content") String content, @Param("id") Long id);


}























