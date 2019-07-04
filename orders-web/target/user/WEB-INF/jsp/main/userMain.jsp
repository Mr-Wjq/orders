<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN">
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	    <title>hun-admin</title>
	    <link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/styles_btwn.css" type="text/css" media="screen" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/system/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/layui-v2.2.2/layui.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}//static/js/main/userMain.js"></script>
	</head>
	<body class="homeW">
		<%@ include file="../system/header.jsp" %>
		<div style="height: 200px;"></div>
		<ul id="home_nav">
			<li>
				<shiro:hasPermission name="system:tongBaoYuJing">
					<a href="${pageContext.request.contextPath}/platform/getTongBaoYuJing" class="ico_home" id="ico_h3">
						<p>通报预警</p>
					</a>
				</shiro:hasPermission>
			</li>
			<li>
				<shiro:hasPermission name="system:situationAwareness">
					<a href="${pageContext.request.contextPath}/platform/getSituationAwareness" class="ico_home" id="ico_h10">
						<p>态势感知</p>
					</a>
				</shiro:hasPermission>
			</li>
			<li>
				<shiro:hasPermission name="system:gradeprotection">
					<a href="${pageContext.request.contextPath}/platform/getGradeprotection" class="ico_home" id="ico_h8">
						<p>等保备案</p>
					</a>
				</shiro:hasPermission>
			</li>
			<li>
				<shiro:hasPermission name="system:toolbox2">
					<a href="${pageContext.request.contextPath}/platform/getToolbox2" class="ico_home" id="ico_h9">
						<p>等保自查</p>
					</a>
				</shiro:hasPermission>
			</li>
		</ul>
	</body>
	<script>
		$(function(){
			fitHeight();
		});
		//页面窗口改变大小时加载
	    $(window).resize(function () {
	        fitHeight()
	    });
		function fitHeight(){
			var height = document.documentElement.clientHeight;
			$('.homeW').css("height",height);
		}
	</script>
</html>
