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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/role.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
    	.layui-layer-content{overflow:hidden !important;}
    	#role_ztree li span.button.ico_open {
		    margin-right: 2px;
		    background: url(/static/css/images/group.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
		#role_ztree li span.button.ico_docu {
		    margin-right: 2px;
		    background: url(/static/css/images/user_gray.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		    vertical-align: top;
		}
		.tree-folder-open {
		    background: url(/static/css/images/group.png) ;
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		}
		.tree-file {
		    background: url(/static/css/images/user_gray.png);
		    background-repeat:no-repeat; 
		    background-size:100% 100%;
		    -moz-background-size:100% 100%;
		}
    </style>
</head>
<body>
<div id="role-tool-bar" class="tab_container">
	<form id="role_search_form">
		<div class="layui-btn-group">
			<shiro:hasPermission name="role:list">
			    <div class="layui-btn layui-btn-primary select-btn" id="role-select-btn"><i class="layui-icon">&#x1002;</i>刷新</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:insert">
			    <div class="layui-btn layui-btn-primary save-btn" id="role-save-btn"><i class="layui-icon">&#xe654;</i>新增</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:update">
			    <div class="layui-btn layui-btn-primary update-btn" id="role-update-btn"><i class="layui-icon">&#xe642;</i>修改</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="role:delete">
			    <div class="layui-btn layui-btn-primary delete-btn" id="role-delete-btn"><i class="layui-icon">&#xe640;</i>删除</div>
			</shiro:hasPermission>
		</div>
	</form>
</div>
<div id="role_grid" style="padding: 10px;display:none;">

</div>

<div id="role_edit_dialog" style="display:none;">
    <form id="role_edit_form">
        <div style="float: left;padding: 15px;width: 20%;height: 95%;">
            <input type="hidden" name="id" id="id">
            <p style="padding: 5px">&nbsp;&nbsp;名称:<input name="name" id="name"
                                                          style="width: 250px;height: 35px"
                                                          data-options="required:true"
                                                          class="easyui-textbox easyui-validatebox"></p>
            <p style="padding: 5px;"> &nbsp;&nbsp;描述:<input name="description" id="description"
                                                            style="width: 250px;height: 130px"
                                                            data-options="required:true,multiline:true"
                                                            class="easyui-textbox easyui-validatebox"></p>
            <p style="padding: 5px;"> &nbsp;&nbsp;首页:<select name="firstPageId" id="firstPageId" style="width: 250px;height: 35px">

                                            </select>
            </p>
        </div>
        <div style="float: right; width: 30%;margin:20px;height: 95%;overflow:auto;">
                               选择权限
            <hr class="layui-bg-red">
            <div id="permissions_ztree" class="zTree" ></div>
        </div>
        <div style="float: right; width: 25%;margin:20px 30px 0  0;height: 95%;overflow:auto;">
         	选择父级角色
         	<hr class="layui-bg-red">
            <div id="role_ztree" class="zTree" ></div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>