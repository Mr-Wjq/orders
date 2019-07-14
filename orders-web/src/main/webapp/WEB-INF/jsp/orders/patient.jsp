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
       <input type="text" id="patientName" placeholder="患者姓名" class="layui-input">
    </div>
    <div class="layui-input-inline">
       <input type="text" id="patientPhone" placeholder="患者手机号" class="layui-input">
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

<div id="insertDiv" style="display: none;padding: 10px;" >
	<form class="layui-form" action="">
	  	<div class="layui-form-item">
		    <label class="layui-form-label">患者姓名<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="patientName" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">年龄</label>
		    <div class="layui-input-block">
		        <input type="text" name="patientAge" required  lay-verify="kongge" placeholder="请输入信息 " onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="3" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">性别</label>
		    <div class="layui-input-block">
				<input type="radio" name="patientSex" value="1" title="男" checked>
      			<input type="radio" name="patientSex" value="2" title="女">		        
		    </div>
	    </div>
	    <div class="layui-form-item">
		    <label class="layui-form-label">手机号<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <input type="text" name="patientPhone" required  lay-verify="phone" placeholder="请输入信息 " maxlength="11" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">状态</label>
		    <div class="layui-input-block">
				<input type="radio" name="patientType" value="1" title="初诊" checked>
      			<input type="radio" name="patientType" value="2" title="复诊">		        
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">治疗类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="baseCureId" lay-verify="required">
			        <option value="">治疗类型</option>
			        <option value="2">修复</option>
			        <option value="3">正畸</option>
			        <option value="4">种植</option>
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
		    <label class="layui-form-label">患者姓名<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		      <input type="text" name="patientName" required  lay-verify="required|kongge" placeholder="请输入信息" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">年龄</label>
		    <div class="layui-input-block">
		        <input type="text" name="patientAge" required  lay-verify="kongge" placeholder="请输入信息 " onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="3" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">性别</label>
		    <div class="layui-input-block">
				<input type="radio" name="patientSex" value="1" title="男" checked>
      			<input type="radio" name="patientSex" value="2" title="女">		        
		    </div>
	    </div>
	    <div class="layui-form-item">
		    <label class="layui-form-label">手机号<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <input type="text" name="patientPhone" required  lay-verify="phone" placeholder="请输入信息 " maxlength="11" autocomplete="off" class="layui-input">
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">状态</label>
		    <div class="layui-input-block">
				<input type="radio" name="patientType" value="1" title="初诊" checked>
      			<input type="radio" name="patientType" value="2" title="复诊">		        
		    </div>
	    </div>
	  	<div class="layui-form-item">
		    <label class="layui-form-label">治疗类型<span class="required-sign">☀</span></label>
		    <div class="layui-input-block">
		        <select name="baseCureId" lay-verify="required">
			        <option value="">治疗类型</option>
			        <option value="2">修复</option>
			        <option value="3">正畸</option>
			        <option value="4">种植</option>
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
<script type="text/javascript" src="static/js/page-js/orders/patient.js?v=<%=version %>"></script>
</body>