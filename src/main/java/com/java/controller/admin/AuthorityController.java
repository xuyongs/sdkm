package com.java.controller.admin;

import com.github.pagehelper.PageInfo;
import com.java.pojo.admin.OneMenu;
import com.java.pojo.admin.SystemUser;
import com.java.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @RequestMapping("/getMenus")
    public @ResponseBody
    List<Map<String, Object>> getMenus(@RequestParam(defaultValue = "0") Long id) {
        //System.out.println("------------------vv------------");
        return authorityService.findMenus(id);
    }

    @RequestMapping("/getRoleMenus")
    public @ResponseBody
    List<Map<String, Object>> getRoleMenus(@RequestParam(defaultValue = "0") Long id, HttpSession session) {
        String account = (String) session.getAttribute("account");

        return authorityService.findRoleMenus(account, id);
    }

    @RequestMapping("/login")
    public String login(String account, String password, HttpSession session) {
        String base = "redirect:/pages/admin/";
        boolean flag = authorityService.findByAccountAndPwd(account, password);
        if (flag) {
            session.setAttribute("account", account);
            return base + "main.jsp";
        }
        return base + "login.jsp";

    }

    @RequestMapping("/getRole")
    public @ResponseBody
    Map<String, Object> getRole(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "3") Integer rows) {
        System.out.println("adddddff");
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String, Object>> rolesList = authorityService.findRoles(page, rows);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(rolesList);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @RequestMapping("/getRoleAuthority")
    public @ResponseBody
    List<Map<String, Object>> getRoleAuthority(@RequestParam(defaultValue = "0") Long id) {
        return authorityService.findRoleAuthority(id);
    }

    @RequestMapping("/addRole")
    public @ResponseBody
    Map<String, Object> addRole(String roleName, String authorityIdStr) {

        return authorityService.saveRole(roleName, authorityIdStr);

    }

    @RequestMapping("/getSystemUser")
    public @ResponseBody
    Map<String, Object> getSystemUser(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "3") Integer rows) {

        System.out.println("mmmmmmmmmmmmmmm");
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String, Object>> rolesList = authorityService.findSystemUsers(page, rows);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(rolesList);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @RequestMapping("/getPTRoles")
    public @ResponseBody
    List<Map<String, Object>> getPTRoles() {
        return authorityService.findPTRoles();
    }

    @RequestMapping("/addSystemUser")
    public @ResponseBody Map<String,Object> addSystemUser(SystemUser user){
        return authorityService.saveSystemUser(user);
    }

    @RequestMapping("/getPTAuthority")
    public  String getPTAuthority(Model model,Long roleId){
        List<OneMenu> ptAuthorityList = authorityService.findPTAuthority();
        model.addAttribute("ptAuthorityList",ptAuthorityList);
        model.addAttribute("roleId",roleId);
        return "admin/systemManager/editRoleContent.jsp";
    }

    @RequestMapping("/getAIdByRoleId")
    public @ResponseBody List<Long> getAIdByRoleId(Long roleId){
        return authorityService.findAIdbyRoleId(roleId);
    }

    @RequestMapping("/updateRoleAndAuthority")
    public boolean updateRoleAndAuthority(Long roleId,String idStr){
        return authorityService.modifyRoleAndAuthorityRelation(roleId,idStr);
    }
}
























