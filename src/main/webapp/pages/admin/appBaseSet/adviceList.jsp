<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>意见反馈</title>
</head>
<body>
<table id="dg"></table>
<script type="text/javascript">
    function singleUpdate(temp) {
        $.ajax({
            url: "<%=basePath%>/admin/appBaseSet/oneUpdate.do",
            type: "POST",
            dataType: "JSON",
            data: {
                "id": temp
            },
            success: function (rs) {
                if (rs) {
                    $("#dg").datagrid("reload");
                } else {
                    $.messager.alert('提示', '处理失败');
                }
            },
            error: function (rs) {
                $.messager.alert('提示', '出错了');
            }
        })
    }


    $(function () {
        $("#dg").datagrid({
            fit: true,
            rownumbers: true,
            url: "<%=basePath %>/admin/appBaseSet/getAdvices.do",
            striped: true,
            columns: [
                [
                    {title: '全选', checkbox: true},
                    /*{title: '序号', field: 'rownumbers'},*/
                    {title: '意见内容', field: 'content', width: 150},
                    {title: '时间', field: 'createTime', width: 150, sortable: true},
                    {title: '姓名', field: 'uName', width: 150},
                    {
                        title: '联系方式', field: 'phone', width: 150, formatter: function (value, row, index) {
                            if (value == undefined) {
                                return "<span style='color: red'>无</span>";
                            } else {
                                return value;
                            }
                        }
                    },
                    {
                        title: '是否已处理', field: 'status', width: 150, formatter: function (value, row, index) {
                            if (value == '0') {
                                //return "未处理";
                                return "<a href='javascript:void(0)' class='s' onclick='singleUpdate(" + row.id + ")'>未处理</a>"
                            } else {
                                //return '已处理';
                                return "<a href='javascript:void(0)' class='s' onclick='singleUpdate(" + row.id + ")'>已处理</a>"
                            }
                        }
                    },
                ]
            ],
            sortName: 'createTime',
            remoteSort: false,
            pagination: true,
            //pageList:[1,5,10,20],
            toolbar: [
                {
                    text: "已处理",
                    iconCls: 'icon-edit',
                    handler: function () {
                        var rows = $("#dg").datagrid("getSelections");
                        var len = rows.length;
                        if (len == 0) {
                            $.messager.alert('提示', '没有选中记录！');
                        } else {
                            var idStr = "";
                            for (var i in rows) {
                                idStr += rows[i].id + ",";
                            }
                            $.ajax({
                                url: "<%=basePath%>/admin/appBaseSet/batchUpdate.do",
                                type: "POST",
                                dataType: "JSON",
                                data: {
                                    "idStr": idStr
                                },
                                success: function (rs) {
                                    if (rs) {
                                        $("#dg").datagrid("reload");
                                    } else {
                                        $.messager.alert('提示', '请重试');
                                    }
                                },
                                error: function (rs) {
                                    $.messager.alert('提示', '出错了');
                                }
                            })
                        }
                    }
                }, '-',
                {
                    text: "批量删除",
                    iconCls: 'icon-remove',
                    handler: function () {
                        alert("删除");
                    }
                }
            ],
        });


        /*$(".s").linkbutton({
            iconCls:'icon-edit',
        });*/
    });
</script>
</body>
</html>