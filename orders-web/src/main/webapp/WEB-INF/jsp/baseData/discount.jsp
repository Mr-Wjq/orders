<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<%@ include file="/WEB-INF/jsp/system/top.jsp" %>
	<style type="text/css">
		.layui-layer-page .layui-layer-content{overflow:initial !important;}
		.layui-form-label { width: 110px;}
		.layui-input-block {margin-left: 150px;}
	</style>
</head>
<body>
<form class="layui-form" action="" style="margin-top: 20px;">
<div class="layui-form-item">
    <label class="layui-form-label">查询</label>
    
    <div class="layui-input-inline">
       <input type="text" id="discountName" placeholder="优惠券" class="layui-input">
    </div>
    <div class="layui-input-inline">
      <select id="discountType">
        <option value="">优惠券类型</option>
        <option value="1">工厂优惠券</option>
        <option value="2">产品材质优惠券</option>
        <option value="3">平台优惠券</option>
      </select>
    </div>
    <div class="layui-input-inline">
      <button class="layui-btn" type="button" id="searchBtn">
		  <i class="layui-icon">&#xe615;</i>查询
	  </button>
    </div>
</div>
</form>
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
		    <label class="layui-form-label">优惠券<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="discountName" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">服务金额<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <input type="text" name="discountPrice" required  lay-verify="required|money" placeholder="请输入金额 " maxlength="10" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">优惠券类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="discountType" lay-filter="i_discountType" lay-verify="required">
			        <option value="">优惠券类型</option>
			        <option value="1">工厂优惠券</option>
			        <option value="2">产品材质优惠券</option>
			        <option value="3">平台优惠券</option>
			    </select>
		    </div>
	    </div>
	    <div class="layui-form-item" id="i_factoryIdDiv" style="display: none;">
		    <label class="layui-form-label">指定工厂<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="factoryId" id="i_factoryId" lay-verify="required">
			        <option value="">请选择工厂</option>
			    </select>
		    </div>
	    </div>
	    <div class="layui-form-item" id="i_baseProductIdDiv" style="display: none;">
		    <label class="layui-form-label">指定产品材质<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="baseProductId" id="i_baseProductId" lay-verify="required">
			        <option value="">请选择产品材质</option>
			    </select>
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
		    <label class="layui-form-label">优惠券<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="discountName" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">服务金额<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <input type="text" name="discountPrice" required  lay-verify="required|money" placeholder="请输入金额 " maxlength="10" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">优惠券类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="discountType" lay-filter="u_discountType" lay-verify="required">
			        <option value="">优惠券类型</option>
			        <option value="1">工厂优惠券</option>
			        <option value="2">产品材质优惠券</option>
			        <option value="3">平台优惠券</option>
			    </select>
		    </div>
	    </div>
	    <div class="layui-form-item" id="u_factoryIdDiv" style="display: none;">
		    <label class="layui-form-label">指定工厂<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="factoryId" id="u_factoryId" lay-verify="required">
			        <option value="">请选择工厂</option>
			    </select>
		    </div>
	    </div>
	    <div class="layui-form-item" id="u_baseProductIdDiv" style="display: none;">
		    <label class="layui-form-label">指定产品材质<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="baseProductId" id="u_baseProductId" lay-verify="required">
			        <option value="">请选择产品材质</option>
			    </select>
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
<script type="text/javascript" src="static/js/page-js/baseData/discount.js?v=<%=version %>"></script>
</body>