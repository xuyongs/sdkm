package com.java.service;

import com.java.pojo.admin.OneMenu;
import com.java.pojo.admin.SystemUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AuthorityService {
    List<Map<String, Object>> findMenus(Long id);

    boolean findByAccountAndPwd(String account, String password);

    List<Map<String, Object>> findRoleMenus(String account, Long id);

    List<Map<String, Object>> findRoles(Integer pageNum, Integer pageSize);

    List<Map<String, Object>> findRoleAuthority(Long id);


    Map<String, Object> saveRole(String roleName, String authorityIdStr);

    List<Map<String, Object>> findSystemUsers(Integer pageNum, Integer pageSize);

    List<Map<String, Object>> findPTRoles();

    int selectAccountIsExist(String account);

    Map<String, Object> saveSystemUser(SystemUser user);

    List<OneMenu> findPTAuthority();

    List<Long> findAIdbyRoleId(Long roleId);

    boolean modifyRoleAndAuthorityRelation(Long roleId,String idStr);
}







































