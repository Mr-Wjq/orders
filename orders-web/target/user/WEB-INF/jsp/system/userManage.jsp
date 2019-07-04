<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>cloud-shield</title>
    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
    <%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/zTree_v3/css/zTreeStyle/zTreeStyle.css"/> --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/system/index.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/datagrid-groupview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/customValidation.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/zTree_v3/js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/userManage.js"></script>
</head>
<body class="layui-layout-body kit-theme">
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header">
			<%@ include file="../system/header.jsp" %>
		</div>
		<div class="layui-side layui-bg-black kit-side">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree fsLeftMenu"  lay-filter="fsLeftMenu" id="fsLeftMenu">
					<shiro:hasPermission name="user:unit">
						<li class="layui-nav-item layui-nav-itemed">
							<dd class="layui-this">
								<input name="unitId" id="unitId" value='${pageContext.request.contextPath}/user/toUnit' type="text" style="display:none"/>
								<a onclick="getUrl('unitId')" >单位管理</a>
							</dd>
						</li>
					</shiro:hasPermission>
					<li class="layui-nav-item layui-nav-itemed">
						<shiro:hasPermission name="user:unitUser">
							<a class="" href="javascript:;">用户管理</a>
							<dl class="layui-nav-child">
								<c:forEach items="${roleList}" varStatus="i" var="role" >
									<dd>
										<input id="unitUserId${i.index }" value='${pageContext.request.contextPath}/user/toUnitUser?roleId=${role.id }' type="text" style="display:none"/>
										<a onclick="getUrl('unitUserId${i.index }')">${role.name }</a>
									</dd>
								</c:forEach>
							</dl>
						</shiro:hasPermission>
					</li>
					<shiro:hasPermission name="system:role_power">
						<li class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;">角色/权限管理</a>
							<dl class="layui-nav-child">
								<shiro:hasPermission name="role:list">
									<dd>
										<input name="roleId" id="roleId" value='${pageContext.request.contextPath}/role/role' type="text" style="display:none"/>
										<a onclick="getUrl('roleId')">角色管理</a>
									</dd>
								</shiro:hasPermission>
								<shiro:hasPermission name="permission:list">
									<dd>
										<input name="permissionId" id="permissionId" value='${pageContext.request.contextPath}/permission/permission' type="text" style="display:none"/>
										<a onclick="getUrl('permissionId')">权限管理</a>
									</dd>
								</shiro:hasPermission>
							</dl>
						</li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div  class="layui-body">
			<!-- 内容主体区域 -->
			<iframe id="option" name="option" src="" style="overflow: visible;padding:5px;box-sizing:border-box;background: #f0eef5;" scrolling="auto" frameborder="no" width="100%"></iframe>
		</div>
	</div>
	<script>
		$(function(){
			fixHeight();
			$(".layui-nav-tree li:first-child>dl>dd:eq(0)").addClass("layui-this");
			var url1 = $(".layui-nav-tree li:first-child>dl>dd:eq(0) input:eq(0)").val();
            var url2 = $('#unitId').val();
            if (url2 != null && url2 != "" && url2 != undefined){
                addTab(url2);
			}else {
                addTab(url1);
            }
        })
		function getUrl(id){
			var url = $('#'+id).val();
			addTab(url);
		}
		function addTab(url){
			$("#option").prop("src",url);
	    }
		function fixHeight() {
			var ele =document.getElementById("option");
			ele.style.height = (window.innerHeight-60)+'px';
		}
		$(window).resize(function () {
			fixHeight();
		}).resize();
	</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
</html>