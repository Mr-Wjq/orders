<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户管理</title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link type="image/x-icon" rel="bookmark" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/layui-v2.2.2/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/system/index.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/datagrid-groupview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/customValidation.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree_v3/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/data.js"></script>
	<style>
		.l-btn-text{line-height:36px !important;}
		.l-btn-plain:hover{padding:0 18px;}
	</style>
</head>
<body>
<div id="data-tool-bar" class="tab_container">
	<div class="layui-btn-group">
	    <div class="layui-btn layui-btn-primary easyui-menubutton" menu="#save-data-btn"><i class="layui-icon">&#xe654;</i>新增</div>
	    <div class="layui-btn layui-btn-primary" id="delete-data"><i class="layui-icon">&#xe640;</i>删除</div>
	    <div class="layui-btn layui-btn-primary update-btn" id="update-data"><i class="layui-icon">&#xe642;</i>修改</div>
	    <div class="layui-btn layui-btn-primary delete-btn" id="flash-data"><i class="layui-icon">&#x1002;</i>刷新</div>
	</div>
</div>
<div id="data_grid" style="padding: 10px">

</div>
<div id="save-data-btn">
    <div data-options="iconCls:'icon-add' " id="save-data-data" style="width:70px">字典</div>
    <div data-options="iconCls:'icon-add'" id="save-data-group" style="width:70px">字典组</div>
</div>

<div id="save-data-dialog" style="display: none;">

    <form action="#" id="save-data-form">
        <input type="hidden" id="id" name="id">
        <div style="float: left;height: 300px;">
            <p style="padding: 10px;">&nbsp;&nbsp;名称:<input name="data_keyName" id="data_keyName"
                                                            style="width: 300px;height: 35px"
                                                            data-options="required:true"
                                                            class="easyui-textbox easyui-validatebox"></p>
            <p style="padding: 10px ;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;值:&nbsp;<input name="data_keyValue" id="data_keyValue"
                                                                  style="width: 300px;height: 35px"
                                                                  data-options="required:true"
                                                                  class="easyui-textbox easyui-validatebox"></p>
            <p style="padding: 10px;"> &nbsp;&nbsp;描述:<input name="data_description" id="data_description"
                                                             style="width: 300px;height: 130px"
                                                             data-options="required:true,multiline:true"
                                                             class="easyui-textbox easyui-validatebox">
            </p>
        </div>
        <div style="float: right; width: 40%; height:326px;">
        	<div id="data-group" ></div>
        </div>
    </form>
</div>
</div>

<div id="save-data-group-dialog" style="display: none;">
    <div>
        <form id="save-data-group-form" style="padding: 10px;">
            <div style="float: left;">
                <p style="padding: 10px;">&nbsp;&nbsp;名称:<input name="data_name" id="data_group_name"
                                                                style="width: 300px;height: 35px"
                                                                data-options="required:true"
                                                                class="easyui-textbox easyui-validatebox"></p>
                <p style="padding: 10px;"> &nbsp;&nbsp;描述:<input name="data_description" id="data_group_description"
                                                                 style="width: 300px;height: 130px"
                                                                 data-options="required:true,multiline:true"
                                                                 class="easyui-textbox easyui-validatebox">
                </p>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>
