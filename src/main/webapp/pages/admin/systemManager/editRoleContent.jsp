<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编辑角色页面</title>
</head>
<body>
<center>
    <table id="roleContent" border="1px" width="500px" cellspacing="0px">
        <c:forEach items="${ptAuthorityList}" var="oneMenu">
            <tr>
                <td><input type="hidden" name="oneId" value="${oneMenu.oneId}"> ${oneMenu.oneName}</td>
                <td>
                    <c:forEach items="${oneMenu.twoMenuList}" var="twoMenu">
                        <input type="checkbox" flag="${oneMenu.oneId}" name="twoId" value="${twoMenu.twoId}">${twoMenu.twoName}&nbsp;&nbsp;
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" value="修改"> </td>
            <td><input type="button" value="重置"> </td>
        </tr>
    </table>
</center>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"<%=basePath%>/admin/authority/getAIdByRoleId.do",
            type:"POST",
            dataType:"JSON",
            data:{
                "roleId":${roleId}
            },
            success:function (rs) {
                for (var i in rs) {
                    $("input[value="+rs[i]+"]").prop("checked",true)
                }
            },
            error:function (rs) {

            }
        })

        $("#eRole").click(function () {
            var len = $("input[type=checkbox]:checked").size();
            if (len>=1){
                var $twoId = $("#roleContent input[type=checkbox]:checked")
                var twoIdStr = "";
                $twoId.each(function (index, dom) {
                    var $d = $(dom);
                    twoIdStr+=$d.val()+","+$d.attr("flag")+",";
                })

                $.ajax({
                    url:"<%=basePath%>/admin/authority/updateRoleAndAuthority.do",
                    type:"POST",
                    dataType:"JSON",
                    data:{
                        "idStr":idStr,
                        "roleId":${roleId}
                    },
                    success:function (rs) {
                        if (rs) {
                            $("#editRole").window("close");
                            $("#roleDg").datagrid("reload");
                            $.messager.show({
                                title:"提示",
                                msg:"修改成功",
                                timeout:3000,
                                showType:"slide"
                            })
                        }else {
                            $.messager.alert("提示","请重试");
                        }
                    },
                })
            } else {
                $.messager.alert("提示","请至少勾选一个权限")
            }
        })
    })
</script>
</body>
</html>
























