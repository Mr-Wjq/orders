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
       <input type="text" id="kousaoyiName" placeholder="品牌" class="layui-input">
    </div>
    
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>

<!-- 数据表格 -->
<table class="layui-hide" id="kousaoyiTableId" lay-filter="kousaoyiTableFilter"></table>

<script type="text/html" id="kousaoyiTableToolbar">
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
		    <label class="layui-form-label">品牌</label>
		    <div class="layui-input-block">
		      <input type="text" name="kousaoyiName" required  lay-verify="required|kongge" placeholder="请输入品牌" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">启动链接</label>
		    <div class="layui-input-block">
		        <textarea name="openMethod" placeholder="请输入内容" lay-verify="required|kongge" class="layui-textarea"></textarea>
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
		    <label class="layui-form-label">品牌</label>
		    <div class="layui-input-block">
		      <input type="text" name="kousaoyiName" required  lay-verify="required|kongge" placeholder="请输入品牌" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">启动链接</label>
		    <div class="layui-input-block">
		        <textarea name="openMethod" placeholder="请输入内容" lay-verify="required|kongge" class="layui-textarea"></textarea>
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
<script type="text/javascript" src="static/js/page-js/baseData/kousaoyi.js?v=<%=version %>"></script>
</body>