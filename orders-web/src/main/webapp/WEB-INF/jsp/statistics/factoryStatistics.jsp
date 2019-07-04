<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>

</head>
<body>

<div style="margin-top: 20px;">
	<div class="layui-form-item">
	    <label class="layui-form-label">发布时间</label>
	    <div class="layui-input-inline">
	      <input type="text" id="createTime" placeholder="发布时间" class="layui-input">
	    </div>
	    <div class="layui-input-inline">
	      <button class="layui-btn" type="button" id="searchBtn">
			  <i class="layui-icon">&#xe615;</i>查询
		  </button>
	    </div>
	</div>
</div>
<table class="layui-hide" id="factoryStatisticsTable"></table>

<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/statistics/factoryStatistics.js"></script>
</body>