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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/online.js"></script>
<body>
<div id="online-tool-bar" class="tab_container">
	<div class="layui-btn-group">
	    <div class="layui-btn layui-btn-primary select-btn" id="online-logout-btn"><i class="layui-icon">&#x1007;</i>强制下线</div>
	    <div class="layui-btn layui-btn-primary save-btn" id="online-flash-btn"><i class="layui-icon">&#x1002;</i>刷新页面</div>
	</div>
    <div class="layui-inline" style="background:#eee;">
	   	<label class="layui-form-label">登录名:</label>
	   	<div class="layui-input-inline">
	     	<input type="text" id="login_name" class="layui-input">
	   	</div>
	</div>
	<div class="layui-inline" style="background:#eee;">
	   	<label class="layui-form-label">中文名:</label>
	   	<div class="layui-input-inline">
	     	<input type="text" id="zh_name" class="layui-input">
	   	</div>
	</div>
    <div class="layui-btn-group">
		<div class="layui-btn layui-btn-normal" id="online-select-btn"><i class="layui-icon">&#xe615;</i>搜索</div>
	</div>
</div>
<div id="online_grid" style="padding: 10px">

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>