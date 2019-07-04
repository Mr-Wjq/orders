<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>

</head>
<body>
	<shiro:hasPermission name="system:hongtan4">
	</shiro:hasPermission>
	HELLO WORD 
	<a href="system/aaa" >跳转链接</a>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
</body>