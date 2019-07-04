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
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main/superAdminMain.js"></script>
	</head>
	<body class="homeW">
		<%@ include file="../system/header.jsp" %>
		<div style="height: 200px;"></div>
		<ul id="home_nav">
			<li><a onclick="toMoKuai(1)" class="ico_home" id="ico_h6"><p style="">用户管理</p></a></li>
			<li><a onclick="toMoKuai(2)" class="ico_home" id="ico_h9"><p style="">系统管理</p></a></li>
			<li><a href="${pageContext.request.contextPath}/platform/getProbe" class="ico_home" id="ico_h2"><p style="">探针管理</p></a></li>
			<!-- <li><a onclick="toMoKuai(3)" class="ico_home" id="ico_h4"><p style="">资产监测</p></a></li> -->
			
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
