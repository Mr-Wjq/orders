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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/log.js"></script>
<body>
<div id="log-tool-bar" class="tab_container">
    <form id="select_form">
    	<div class="layui-btn-group">
			<div class="layui-btn layui-btn-primary" id="log-flash-btn"><i class="layui-icon">&#x1002;</i>刷新</div>
		</div>
        <!-- <div class="easyui-linkbutton " id="log-flash-btn" data-options="iconCls:'icon-reload'" style="width:70px">刷新
        </div> -->
        <div class="layui-inline" style="background:#eee;">
		   	<label class="layui-form-label">method:</label>
		   	<div class="layui-input-inline">
		     	<input type="text" name="method" class="layui-input">
		   	</div>
		</div>
		<div class="layui-inline" style="background:#eee;">
		   	<label class="layui-form-label">url:</label>
		   	<div class="layui-input-inline">
		     	<input type="text" name="url" class="layui-input">
		   	</div>
		</div>
		<div class="layui-inline" style="background:#eee;">
		   	<label class="layui-form-label">param:</label>
		   	<div class="layui-input-inline">
		     	<input type="text" name="param" class="layui-input">
		   	</div>
		</div>
		<div class="layui-inline" style="background:#eee;">
		   	<label class="layui-form-label">result:</label>
		   	<div class="layui-input-inline">
		     	<input type="text" name="result" class="layui-input">
		   	</div>
		</div>
        <!-- <span style="line-height: 26px; ">method:<input name="method" class="easyui-textbox"
                                                        style="line-height: 26px; "></span> -->
        <!-- <span style="line-height: 26px; ">url:<input name="url" class="easyui-textbox"
                                                     style="line-height: 26px; "></span>
        <span style="line-height: 26px; ">param:<input name="param" class="easyui-textbox" style="line-height: 26px; "></span>
        <span style="line-height: 26px; ">result:<input name="result" class="easyui-textbox"
                                                        style="line-height: 26px; "></span>&nbsp;
        <div class="easyui-linkbutton " id="log-select-btn" data-options="iconCls:'icon-search'" style="width:70px">搜索
        </div> -->
        <div class="layui-btn-group">
			<div class="layui-btn layui-btn-normal" id="log-select-btn"><i class="layui-icon">&#xe615;</i>搜索</div>
		</div>
    </form>
</div>
<div id="log_grid" style="padding: 10px">

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>