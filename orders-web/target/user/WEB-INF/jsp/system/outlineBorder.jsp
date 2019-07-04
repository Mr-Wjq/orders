<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN">
<html>
	<head>
	<meta charset="utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/static/image/favicon.png">
	<link type="image/x-icon" rel="bookmark" href="${pageContext.request.contextPath}/static/image/favicon.png">
	<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath }/static/css/styles.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/system/outlineBorder.js"></script>
	<title></title>
	</head>
	<body class="layui-layout-body kit-theme">
		<div class="layui-layout layui-layout-admin kit-layout-admin">
			<div id="header">
				<%@ include file="../system/header.jsp" %>
			</div>
			<div id="comment">
				<iframe width="100%" height="556px" align="center" id="frameId" name="frameId" frameborder="0" scrolling="auto"  src=""></iframe>
			</div>
		</div>
	</body>
	<input type="hidden" name="config_data" id="config_data" value='${configData}'/>
</html>