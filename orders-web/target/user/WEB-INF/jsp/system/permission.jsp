<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>cloud-shield</title>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/permission.js"></script>
    
</head>
<body>
<div id="permission-tool-bar" class="tab_container">
	<form id="permission-search-form">
		<div class="layui-btn-group">
		    <div class="layui-btn layui-btn-primary select-btn" id="flash-permission"><i class="layui-icon">&#x1002;</i>刷新</div>
		    <div class="layui-btn layui-btn-primary save-btn" onclick="insertPermissionShow()"><i class="layui-icon">&#xe654;</i>新增</div>
		    <div class="layui-btn layui-btn-primary update-btn" onclick="updatePermissionShow()"><i class="layui-icon">&#xe642;</i>修改</div>
		    <div class="layui-btn layui-btn-primary delete-btn" id="delete-permission"><i class="layui-icon">&#xe640;</i>删除</div>
		</div>
	</form>
</div>
<div id="permission_grid" style="padding: 10px; overflow:auto; width:100%; height:89%;">
</div>
<!-- <div id="save-permission-btn">
    <div data-options="iconCls:'icon-add' " id="save-permission-permission" style="width:70px">权限</div>
    <div data-options="iconCls:'icon-add'" id="save-permission-group" style="width:70px">权限组</div>
</div> -->

<div id="save-permission-dialog" style="display: none;">
   <form  action="#" id="save-permission-form">
       <input type="hidden" id="permission_id" name="permission_id">
       <div style="float: left;width: 50%;height: 80%;">
           <p style="padding: 10px;">&nbsp;&nbsp;名称:<input name="permission_name" id="permission_name"
                                                           style="width: 300px;height: 35px"
                                                           data-options="required:true"
                                                           class="easyui-textbox easyui-validatebox"></p>
           <p style="padding: 10px ;">&nbsp;&nbsp;编码:<input name="permission_code" id="permission_code"
                                                            style="width: 300px;height: 35px"
                                                            data-options="required:true"
                                                            class="easyui-textbox easyui-validatebox"></p>
           <p style="padding: 10px;"> &nbsp;&nbsp;描述:<input name="permission_description" id="permission_description"
                                                            style="width: 300px;height: 130px"
                                                            data-options="required:true,multiline:true"
                                                            class="easyui-textbox easyui-validatebox">
           </p>
       </div>
       <div style="float: right; width: 48%;height: 80%;">
            <div id="permission_list" ></div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>
