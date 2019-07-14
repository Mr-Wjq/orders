<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
</head>
<body>

<!-- 数据表格 -->
<table class="layui-hide" id="tableId" lay-filter="tableFilter"></table>

<script type="text/html" id="tableToolbar">
  <div class="layui-btn-container">
	<button class="layui-btn layui-btn-sm" lay-event="insert">新增</button>
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
  </div>
</script>

<script type="text/html" id="lineToolbar">
  	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
  	<a class="layui-btn layui-btn-xs" lay-event="test">测试</a>
</script>

<div id="insertDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="">
	  	<div class="layui-form-item">
		    <label class="layui-form-label">来源</label>
		    <div class="layui-input-block">
		      <input type="text" name="fromName" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">服务金额</label>
		    <div class="layui-input-block">
		        <input type="text" name="fuwufei" required  lay-verify="required|money" placeholder="请输入金额 "  maxlength="10" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="insert">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	</form>    
</div>

<div id="updateDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="" lay-filter="updateFilter">
		<input type="hidden" name="id" />
	  	<div class="layui-form-item">
		    <label class="layui-form-label">来源</label>
		    <div class="layui-input-block">
		      <input type="text" name="fromName" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">服务费</label>
		    <div class="layui-input-block">
		        <input type="text" name="fuwufei" required  lay-verify="required|money" placeholder="请输入金额 " maxlength="10" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	    <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit="" lay-filter="update">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> 
	</form> 
</div>
<%@ include file="/WEB-INF/jsp/system/bottom.jsp" %>
<script type="text/javascript" src="static/js/page-js/baseData/unitFrom.js?v=<%=version %>"></script>
</body>