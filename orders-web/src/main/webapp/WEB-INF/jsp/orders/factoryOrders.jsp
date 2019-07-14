<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<style type="text/css">
		.layui-layer-page .layui-layer-content{overflow:initial !important;}
	</style>

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
      <input type="text" id="createUnitName" placeholder="诊所/医院名称" class="layui-input">
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

<div id="refuseDiv"  style="display: none;padding: 20px;">
	<textarea id="refuseReason" placeholder="请输入内容" maxlength="255" class="layui-textarea"></textarea>
</div>
<div id="expressDiv" style="display: none;padding: 20px;">
    <form class="layui-form">	
	      <select id="expressName" lay-verify="required" lay-search>
	        <option value="">暂无数据</option>
	      </select>
	      <div style="margin-top: 10px;">
	      <input type="text" id="expressNum" required  lay-verify="required" placeholder="请输入快递编号" maxlength="50" autocomplete="off" class="layui-input">
	      </div>
	</form>
</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/orders/factoryOrders.js?v=<%=version %>"></script>
</body>