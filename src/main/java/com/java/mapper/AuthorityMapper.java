package com.java.mapper;

import com.java.pojo.admin.OneMenu;
import com.java.pojo.admin.SystemUser;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface AuthorityMapper {

    @Select("SELECT * FROM system_authoriy WHERE parentId=#{0}")
    List<Map<String, Object>> selectMenus(Long id);

    @Select("SELECT COUNT(*) FROM system_users WHERE account=#{0} AND pwd=#{1} AND STATUS='1'")
    int selectByAccountAndPwd(String account, String password);

    @Select("SELECT sa.* FROM system_roles sr INNER JOIN system_role_authority sra " +
            "ON sr.id=sra.roleId INNER JOIN system_authoriy sa ON sa.id=sra.authorityId " +
            "INNER JOIN system_users su ON su.roleId=sra.roleId WHERE su.account=#{0} " +
            "AND sa.parentId=#{1}")
    List<Map<String, Object>> selectRoleMenus(String account, Long id);


    @Select("SELECT * FROM system_roles")
    List<Map<String, Object>> slectRoles();

    @Select("SELECT * FROM system_authoriy WHERE flag='0' AND parentId=#{0}")
    List<Map<String, Object>> selectRoleAuthority(Long id);




    /*@Select("SELECT COUNT(*) FROM system_roles WHERE roleName=#{roleName}")
    int selectRoleNameExists(String roleName);

    @Insert("INSERT INTO system_roles VALUES(NULL,#{roleName},'1',NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "roleId", keyColumn = "id")
    int insertRole(Map<String, Object> paramMap);

    int insertRoleAuthorityRelation(@Param("roleId") Long roleId, @Param("authorityIdAttr") String[] authorityIdAttr);*/

    @Select("SELECT COUNT(*) FROM system_roles WHERE roleName=#{roleName}")
    int selectRoleNameExists(String roleName);

    @Insert("INSERT INTO system_roles VALUES(NULL,#{roleName},'1',NOW(),'0')")
    @Options(useGeneratedKeys = true,keyProperty = "roleId",keyColumn = "id")
    int insertRole(Map<String,Object> paramMap);

    int insertRoleAuthorityRelation(@Param("roleId") Long roleId,@Param("authorityAttr") String[] authorityAttr);


    @Select("SELECT * FROM system_users")
    List<Map<String, Object>> selectSystemUsers();


    @Select("SELECT * FROM system_roles WHERE isRoot='0'")
    List<Map<String, Object>> selectPTRoles();

    @Select("SELECT COUNT(*) FROM system_users WHERE account=#{0}")
    int selectAccountIsExist(String account);

    @Insert("INSERT INTO system_users VALUES(NULL,NULL,#{account1},#{phone1},#{email1},#{uName1},#{pwd1},#{status1},#{roleId1}")
    int insertSystemUser(SystemUser user);

    List<OneMenu> selectPTAuthority();

    @Select("SELECT authorityId FROM system_role_authority WHERE roleId=#{0}")
    List<Long> selectAIdbyRoleId(Long roleId);


    @Delete("DELETE FROM system_role_authority WHERE roleId=#{0}")
    int deleteRoleAndAuthorityRelation(Long roleId);




}





































