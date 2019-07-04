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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/firstPage.js"></script>

<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
	<div id="user-tool-bar" class="tab_container">
        <div class="layui-btn-group">
        	<shiro:hasPermission name="firstPage:list">
			    <div class="layui-btn layui-btn-primary delete-btn" id="confing-flash-btn"><i class="layui-icon">&#x1002;</i>刷新</div>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="firstPage:insert">
			    <div class="layui-btn layui-btn-primary select-btn" id="confing-save-btn"><i class="layui-icon">&#xe654;</i>新增</div>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="firstPage:update">
			    <div class="layui-btn layui-btn-primary update-btn" id="confing-update-btn"><i class="layui-icon">&#xe642;</i>修改</div>
        	</shiro:hasPermission>
        	<shiro:hasPermission name="firstPage:delete">
			    <div class="layui-btn layui-btn-primary save-btn" id="confing-delete-btn"><i class="layui-icon">&#xe640;</i>删除</div>
        	</shiro:hasPermission>
		</div>
	</div>
	<div id="confing-tool-bar" style="padding: 10px 10px 10px 10px">
	</div>
	<div class="layui-tab-content" id="setting_grid_height" >
		<div id="setting_grid" class="layui-tab-item layui-show"></div>
	</div>
</div>
<div id="edit_config" style="display: none;">
	<form id="setting_edit_form" >
	<input type="hidden" name="id" id="id">
    <div style="padding: 10px">
       <table class="layui-table">
		 <tbody>
		   <tr>
		     <td>首页名称:</td>
		     <td><input name="firstPageName" id="firstPageName" style="width: 480px;height: 35px"
		                                                   data-options="required:true"
		                                                   class="easyui-textbox easyui-validatebox"></td>
		   </tr>
		   <tr>
		     <td>首页路径:</td>
		     <td><input name="firstPagePath" id="firstPagePath"  style="width: 480px;height: 35px"
		                                                         data-options="required:true"
		                                                         class="easyui-textbox easyui-validatebox"></td>
		   </tr>
		   <tr>
		     <td>配置说明</td>
		     <td>
		     	<textarea name="configDetails" id="configDetails" style="width: 480px;height: 75px"></textarea>
		     </td>
		   </tr>
		  </tbody>
		</table>
     </div>
     </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>