<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统用户列表页面</title>
</head>
<body>
<table id="systemUserDg"></table>
<script type="text/javascript">
    $(function () {
        $("#systemUserDg").datagrid({
            columns: [
                [
                    {title: '全选', checkbox: true},
                    {title: '管理员账号', field: 'account', width: 150},
                    {title: '管理员姓名', field: 'uName', width: 150},
                    {title: '管理员手机', field: 'phone', width: 150},
                    {title: '管理员邮箱', field: 'email', width: 150},
                    {
                        title: '管理员状态', field: 'status', width: 150, formatter: function (value, row, index) {
                            if (value == '1') {
                                return '启用';
                            } else {
                                return '禁用';
                            }
                        }
                    },
                    {
                        title: '角色管理', field: 'manager1', width: 150, formatter: function (value, row, index) {
                            return "<a href='javascript:void(0)'>角色设置</a>"
                        }
                    },
                    {
                        title: '操作', field: 'manager2', width: 150, formatter: function (value, row, index) {
                            return "<a href='javascript:void(0)'>查看</a><a href='javascript:void(0)'>编辑</a><a href='javascript:void(0)'>停用</a>"
                        }
                    },
                ]
            ],
            url: "<%=basePath %>/admin/authority/getSystemUser.do",
            pagination: true,
            fit: true,
            toolbar: [{
                iconCls: 'icon-add',
                text: '添加角色',
                handler: function () {
                    $("#addSystemUser").window('open').panel('refresh', '<%=basePath%>/pages/admin/systemManager/addSystemUserContent.jsp');
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

<div id="addSystemUser"></div>

<script type="text/javascript">
    $(function () {
        $("#addSystemUser").window({
            width: 600,
            height: 400,
            iconCls: 'icon-add',
            minimizable: false,
            maximizable: false,
            title: '添加系统用户',
            resizable: false,
            closed: true,
            modal: true
        })
    })
</script>
</body>
</html>















































