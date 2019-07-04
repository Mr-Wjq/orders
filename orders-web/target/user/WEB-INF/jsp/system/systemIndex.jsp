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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/systemIndex.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="javascript:;">系统工具</a>
						<dl class="layui-nav-child">
							<shiro:hasPermission name="system:firstPage">
								<dd>
									<a onclick="getUrl('firstPageId')" >首页配置</a>
									<input type="text" style="display:none" name="firstPageId" id="firstPageId" value='${pageContext.request.contextPath}/system/toFirstPage'/>
								</dd>
							</shiro:hasPermission>
							<shiro:hasPermission name="system:projectSetting">
								<dd>
									<a onclick="getUrl('projectSettingId')" >系统配置</a>
									<input type="text" style="display:none" name="projectSettingId" id="projectSettingId" value='${pageContext.request.contextPath}/system/toProjectSetting'/>
								</dd>
							</shiro:hasPermission>

							<shiro:hasPermission name="data:list">
								<dd>
								<a onclick="getUrl('dataId')">数据字典</a>
								<input type="text" style="display:none" name="dataId" id="dataId" value='${pageContext.request.contextPath}/system/data'/>
								</dd>
							</shiro:hasPermission>
							<shiro:hasPermission name="user:loginStatu:list">
								<dd>
									<a onclick="getUrl('onlineId')" >在线状态</a>
									<input type="text" style="display:none" name="onlineId" id="onlineId" value='${pageContext.request.contextPath}/system/online'/>
								</dd>
							</shiro:hasPermission>
							<shiro:hasPermission name="log:list">
								<dd>
									<a onclick="getUrl('logId')">系统日志</a>
									<input type="text" style="display:none" name="logId" id="logId" value='${pageContext.request.contextPath}/system/log'/>
								</dd>
							</shiro:hasPermission>
							<shiro:hasPermission name="ip:list">
								<dd>
									<a onclick="getUrl('ipId')">IP访问限制</a>
									<input type="text" style="display:none" name="ipId" id="ipId" value='${pageContext.request.contextPath}/system/ip'/>
								</dd>
							</shiro:hasPermission>
							<shiro:hasPermission name="db:select">
								<dd>
									<a onclick="getUrl('druid')">数据库监控</a>
									<input name="druid" id="druid" value='${pageContext.request.contextPath}/druid/index.html' type="text" style="display:none"/>
								</dd>
							</shiro:hasPermission>
							<dd>
									<a onclick="getUrl('systemMes')">系统信息</a>
									<input name="systemMes" id="systemMes" value='${pageContext.request.contextPath}/system/systemMes' type="text" style="display:none"/>
							</dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<div  class="layui-body">
			<!-- 内容主体区域 -->
			<iframe id="option" name="option" src="" style="overflow: visible;padding:5px;box-sizing:border-box;background: #f0eef5;" scrolling="auto" frameborder="no" width="100%" height="100%" ></iframe>
		</div>
	</div>
	<script>
		$(function(){
			$(".layui-nav-tree li:first-child>dl>dd:eq(0)").addClass("layui-this");
			var url = $(".layui-nav-tree li:first-child>dl>dd:eq(0) input:eq(0)").val();
			addTab(url);
		})
		function getUrl(id){
			var url = $('#'+id).val();
			addTab(url);
		}
		function addTab(url){
			$("#option").prop("src",url);
	    }
	</script>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/skinChange.js"></script>
</html>