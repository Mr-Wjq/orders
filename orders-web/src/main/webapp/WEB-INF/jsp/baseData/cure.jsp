<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<style type="text/css">
		.layui-form-label {
		    width: 120px;
		}
		.layui-input, .layui-textarea {
		    width: 80%;
		}
	</style>
</head>
<body>
<!-- 数据表格 -->
<table class="layui-hide" id="cureTableId" lay-filter="cureTableFilter"></table>

<script type="text/html" id="cureTableToolbar">
  <div class="layui-btn-container">
	<button class="layui-btn layui-btn-sm" lay-event="insert">新增</button>
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
  </div>
</script>

<script type="text/html" id="lineToolbar">
  	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
</script>

<div id="insertCureDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="">
	  	<div class="layui-form-item">
		    <label class="layui-form-label">请输入治疗类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="cureName" required  lay-verify="required|zhName" placeholder="请输入治疗类型" autocomplete="off" class="layui-input">
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
<div id="updateCureDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="">
	  	<input type="hidden" name="id" id="cureId" />
	  	<div class="layui-form-item">
		    <label class="layui-form-label">请输入治疗类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="cureName" required  lay-verify="required|zhName" placeholder="请输入治疗类型" autocomplete="off" class="layui-input">
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
<script type="text/javascript" src="static/js/page-js/baseData/cure.js?v=<%=version %>"></script>
</body>