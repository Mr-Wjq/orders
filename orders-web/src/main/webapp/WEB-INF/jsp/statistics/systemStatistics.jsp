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

<div class="layui-tab layui-tab-brief" lay-filter="tabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">诊所</li>
    <li>工厂</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
    	<table class="layui-hide" id="doctorStatisticsTable"></table>
    </div>
    <div class="layui-tab-item">
    	<table class="layui-hide" id="factoryStatisticsTable"></table>
    </div>
  </div>
</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/statistics/systemStatistics.js"></script>
</body>