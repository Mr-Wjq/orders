<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>

</head>
<body>
<form class="layui-form" action="" style="margin-top: 20px;">
<div class="layui-form-item">
    <label class="layui-form-label">查询</label>
    <div class="layui-input-inline">
       <input type="text" id="ordersNum" placeholder="订单号" class="layui-input">
    </div>
    <div class="layui-input-inline">
       <input type="text" id="patientName" placeholder="患者姓名" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <input type="text" id="receiveUnitName" placeholder="工厂名称" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <select id="status">
        <option value="">状态</option>
        <option value="1">未发布</option>
        <option value="2">待接单</option>
        <option value="3">已拒绝</option>
        <option value="4">已接收</option>
        <option value="5">生产中</option>
        <option value="6">已发货</option>
        <option value="7">订单完成</option>
      </select>
    </div>
    <div class="layui-input-inline">
      <input type="text" id="createTime" placeholder="发布时间" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>
<!-- 数据表格 -->
<table class="layui-hide" id="ordersTable" lay-filter="ordersTableFilter"></table>
<div id="updateOrdersDiv" ></div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/orders/dactorOrders.js?v=<%=version %>"></script>
</body>