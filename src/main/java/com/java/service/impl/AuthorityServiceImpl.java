package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.java.mapper.AuthorityMapper;
import com.java.pojo.admin.OneMenu;
import com.java.pojo.admin.SystemUser;
import com.java.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Map<String, Object>> findMenus(Long id) {
        //System.out.println("-----------oo------");
        System.out.println(authorityMapper.selectMenus(id) + "-----------bb------");

        return authorityMapper.selectMenus(id);
    }

    @Override
    public boolean findByAccountAndPwd(String account, String password) {
        return authorityMapper.selectByAccountAndPwd(account, password) == 1;
    }

    public List<Map<String, Object>> findRoleMenus(String account, Long id) {

        System.out.println(account + "---------" + id);
        return authorityMapper.selectRoleMenus(account, id);
    }

    public List<Map<String, Object>> findRoles(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        System.out.println(pageNum + "-----pp----" + pageSize);
        return authorityMapper.slectRoles();
    }


    public List<Map<String, Object>> findRoleAuthority(Long id) {
        return authorityMapper.selectRoleAuthority(id);
    }

    /*public Map<String,Object> findRoleNameExists(String roleName,String authorityIdStr){
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        if (roleName==null || authorityIdStr==null){
            resultMap.put("status","3");
            return resultMap;
        }
        return authorityMapper.selectRoleNameExists(roleName,authorityIdStr);
    }*/




    public Map<String, Object> saveRole(String roleName, String authorityIdStr) {
        System.out.println(roleName+"vvvvvvvvvvvvvvvv"+authorityIdStr);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "0");
        //status: 0成功,1失败，2角色名重复,3数据格式错误  -1未知错误
        //1、首先校验数据:roleName，字符长度1~10中文   authorityIdStr需要时数字  "1,2,3,"
        if (roleName == null || authorityIdStr == null) {
            resultMap.put("status", "3");
            return resultMap;
        }
        if (!roleName.matches("[\\u4e00-\\u9fa5]{1,10}") || !authorityIdStr.matches("(\\d+,)+")) {
            resultMap.put("status", "3");
            return resultMap;
        }
        //2、判断角色名是否存在
        int flag1 = authorityMapper.selectRoleNameExists(roleName);
        if (flag1 == 1) {//存在
            resultMap.put("status", "2");
            return resultMap;
        }
        //插入角色记录
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("roleName", roleName);
        int flag2 = authorityMapper.insertRole(paramMap);
        if (flag2 == 0) {
            resultMap.put("status", "1");
            return resultMap;
        }
        //插入角色与权限的关联关系
        Long roleId = (Long) paramMap.get("roleId");
        System.out.println(roleId+"==============");
        String[] authorityIdAttr = authorityIdStr.split("\\,");
        int flag3 = authorityMapper.insertRoleAuthorityRelation(roleId, authorityIdAttr);
        if (flag3 == 0) {
            resultMap.put("status", "-1");
            return resultMap;
        }
        return resultMap;
    }




    public List<Map<String, Object>> findSystemUsers(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return authorityMapper.selectSystemUsers();
    }

    public List<Map<String, Object>> findPTRoles() {
        return authorityMapper.selectPTRoles();
    }

    @Override
    public int selectAccountIsExist(String account) {
        return 0;
    }

    @Override
    public Map<String, Object> saveSystemUser(SystemUser user) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status","0");

        int i = authorityMapper.selectAccountIsExist(user.getAccount1());
        if (i==1){
            resultMap.put("status","2");
            return resultMap;
        }
        int i1 = authorityMapper.insertSystemUser(user);
        if (i1==1){
            return resultMap;
        }
        resultMap.put("status","1");
        return resultMap;
    }

    @Override
    public List<OneMenu> findPTAuthority() {
        return authorityMapper.selectPTAuthority();
    }

    @Override
    public List<Long> findAIdbyRoleId(Long roleId) {
        return authorityMapper.selectAIdbyRoleId(roleId);
    }

    @Override
    public boolean modifyRoleAndAuthorityRelation(Long roleId, String idStr) {
        if (roleId==null || idStr==null){
            return false;
        }
        int i = authorityMapper.deleteRoleAndAuthorityRelation(roleId);
        if (i>=1){
            String[] idStrArr = idStr.split("\\,");
            Set<String> tempSet = new HashSet<String>();
            for (String temp:idStrArr){
                tempSet.add(temp);
            }
            idStrArr =  tempSet.toArray(new String[0]);
            int flag = authorityMapper.insertRoleAuthorityRelation(roleId, idStrArr);
            if (flag>=1){
                return true;
            }
        }
        return false;
    }
}




































