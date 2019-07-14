<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Long version = System.currentTimeMillis();
%>
<base href="<%=basePath%>">
<title>牙智云</title>
<meta id="csrf_token" content="${csrf_token}"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="image/x-icon" rel="shortcut icon" href="static/images/favicon.png">
<link rel="stylesheet" type="text/css" href="static/js/layui/css/layui.css"/>
<script type="text/javascript" src="static/js/system/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="static/js/system/common.js"></script>
<style type="text/css">
	.required-sign{color: red;}
	
</style>
<input type="hidden" id="basePath" value="<%=basePath%>"/>