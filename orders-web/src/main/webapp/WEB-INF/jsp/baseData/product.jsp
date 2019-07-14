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
      <select id="search_cureList">
        <option value="">治疗类型</option>
      </select>
    </div>
    <div class="layui-input-inline">
       <input type="text" id="textureName" placeholder="材质" class="layui-input">
    </div>
    <div class="layui-input-inline">
       <input type="text" id="brandName" placeholder="品牌" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <input type="text" id="productName" placeholder="产品名称" class="layui-input">
    </div>
    
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>

<!-- 数据表格 -->
<table class="layui-hide" id="productTableId" lay-filter="productTableFilter"></table>

<script type="text/html" id="productTableToolbar">
  <div class="layui-btn-container">
	<button class="layui-btn layui-btn-sm" lay-event="insert">新增</button>
    <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
  </div>
</script>

<script type="text/html" id="lineToolbar">
  	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
</script>

<div id="insertProductDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="">
	  	<div class="layui-form-item">
		    <label class="layui-form-label">治疗类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <select id="cureList" name="baseCureId" lay-verify="required" lay-search>
		        <option value="">请选择治疗类型</option>
		      </select>
		    </div>
		  </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">材质</label>
		    <div class="layui-input-block">
		      <input type="text" name="textureName" required  lay-verify="kongge" placeholder="请输入材质" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">品牌</label>
		    <div class="layui-input-block">
		      <input type="text" name="brandName" required  lay-verify="kongge" placeholder="请输入品牌" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">产品名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="productName" required  lay-verify="kongge" placeholder="请输入产品名称" autocomplete="off" class="layui-input">
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

<div id="updateProductDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="" lay-filter="updateProductFilter">
		<input type="hidden" name="id" />
	  	<div class="layui-form-item">
		    <label class="layui-form-label">治疗类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <select id="u_cureList" name="baseCureId" lay-verify="required" lay-search>
		        <option value="">请选择治疗类型</option>
		      </select>
		    </div>
		  </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">材质</label>
		    <div class="layui-input-block">
		      <input type="text" name="textureName" required  lay-verify="kongge" placeholder="请输入材质" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">品牌</label>
		    <div class="layui-input-block">
		      <input type="text" name="brandName" required  lay-verify="kongge" placeholder="请输入品牌" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">产品名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="productName" required  lay-verify="kongge" placeholder="请输入产品名称" autocomplete="off" class="layui-input">
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
<script type="text/javascript" src="static/js/page-js/baseData/product.js?v=<%=version %>"></script>
</body>