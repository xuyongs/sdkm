<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加系统用户</title>
</head>
<body>
<center>
    <form action="javascript:void(0)" id="fmSystemUser">
        <table width="500px" border="1px">
            <tr>
                <td>管理员头像</td>
                <td>bigHeadImg.jpg</td>
            </tr>
            <tr>
                <td>管理员姓名</td>
                <td><input name="uName1" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>管理员邮箱</td>
                <td><input name="em1" class="easyui-validatebox" data-options="required:true,validType:'email'"/></td>
            </tr>
            <tr>
                <td>管理员手机</td>
                <td><input name="phone1" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>管理员账号</td>
                <td><input name="account1" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>管理员密码</td>
                <td><input name="pwd1" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input name="checkPwd1" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>管理员状态</td>
                <td>
                    <select name="status1" style="width: 200px">
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>选择角色</td>
                <td>
                    <select name="roleId1" class="easyui-combobox" style="width: 200px">
                        <option value='1'>启用</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <input id="addSUser1" type="submit" value="保存">
                </td>
                <td>
                    <input type="reset" value="取消">
                </td>
            </tr>
        </table>
    </form>
</center>
<script type="text/javascript">

    $(function () {
        $("#addSUser").click(function () {
            var json = $("#fmSystemUser").serializeObject();
            $.ajax({
                url: "<%=basePath%>/admin/authority/addSystemUser.do",
                type: "POST",
                dataType: "JSON",
                data:json,
                //contentType:"application/json",
                success: function (rs) {
                    var flag = rs.status;
                    if (flag=='0'){
                        $("#addSystemUser").window('close');
                        $("#systemUserDg").datagrid("reload");
                        $.messager.show({
                            title:'提示',
                            msg:'成功添加一套记录',
                            timeout:3,
                            showType:'slide'
                        })
                    } else if (flag == '2') {
                        $("input[name=account1]").focus()
                    }else {
                        $.messager.alert("操作失败")
                    }
                    
                }
            })
        })


        $.ajax({
            url: "<%=basePath%>/admin/authority/getPTRoles.do",
            type: "POST",
            dataType: "JSON",
            success: function (rs) {
                var content = "";
                for (var i in rs) {
                    content += "<option value='" + rs[i].id + "'>" + rs[i].roleName + "</option>";
                }
                $("#roleId").html(content);
            }
        })
    })
</script>
</body>
</html>








































