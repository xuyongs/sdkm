<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统管理页面</title>
</head>
<body>
<table id="roleDg"></table>
<script type="text/javascript">
    $(function () {
        $("#roleDg").datagrid({
            columns: [
                [
                    {title: '全选', checkbox: true},
                    {title: '角色名', field: 'roleName', width: 150},
                    {
                        title: '状态', field: 'status', width: 150, formatter: function (value, row, index) {
                            if (row.status == '1') {
                                return '启用';
                            } else {
                                return '禁用';
                            }
                        }
                    },
                    {title: '修改时间', field: 'updateTime', width: 150},
                    {
                        title: '角色操作', field: 'operate1', width: 150, formatter: function (value, row, index) {
                            if (row.isRoot == '1') {
                                return "无操作";
                            } else {
                                if (row.status == '1') {
                                    return "<a href='javascript:void(0)>禁用</a>";
                                } else {
                                    return "<a href='javascript:void(0)>启用</a>";
                                }
                            }

                        }
                    },
                    {
                        title: '权限操作', field: 'operate2', width: 150, formatter: function (value, row, index) {
                            if (row.isRoot == '1') {
                                return "无操作";
                            } else {
                                return "<a href='javascript:void(0)' onclick='editJueSe("+row.id+")'>编辑角色</a>";
                            }
                        }
                    },
                ]
            ],
            url: "<%=basePath %>/admin/authority/getRole.do",
            pagination: true,
            fit: true,
            toolbar: [{
                iconCls: 'icon-add',
                text: '添加角色',
                handler: function () {
                    $("#addRole").window('open').panel('refresh', '<%=basePath%>/pages/admin/systemManager/addRoleContent.jsp');
                }
            }, '-', {
                iconCls: 'icon-remove',
                text: '删除角色',
                handler: function () {
                    alert(2)
                }
            }]
        })
    })
</script>
<div id="addRole">
</div>
<script type="text/javascript">


    $(function () {
        $("#addRole").window({
            width: 600,
            height: 400,
            iconCls: 'icon-add',
            minimizable: false,
            maximizable: false,
            title: '添加角色',
            resizable: false,
            closed: true,
            modal: true
        })
    })

</script>

<div id="editRole">
</div>
<script type="text/javascript">

    function editJueSe(roleId){
        $("#editRole").window("open").panel("refresh","<%=basePath%>/admin/authority/getPTAuthority.do?roleId="+roleId);
    }
    $(function () {
        $("#editRole").window(
            width: 600,
            height: 400,
            iconCls: 'icon-edit',
            minimizable: false,
            maximizable: false,
            title: '编辑角色',
            resizable: false,
            closed: true,
            modal: true
        })
    })
</script>
</body>
</html>















































